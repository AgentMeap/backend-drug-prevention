package com.group7.swp391.drug_prevention.controller;

import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.request.ReqLoginDTO;
import com.group7.swp391.drug_prevention.domain.response.ResLoginDTO;
import com.group7.swp391.drug_prevention.service.UserService;
import com.group7.swp391.drug_prevention.util.SecurityUtil;
import com.group7.swp391.drug_prevention.util.annotation.ApiMessage;
import com.group7.swp391.drug_prevention.util.error.IdInvalidException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final SecurityUtil securityUtil;
    private final UserService userService;

    @Value("${project.jwt.refresh-token-validity-in-seconds}")
    private long refreshTokenExpiration;

    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder, SecurityUtil securityUtil, UserService userService) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.securityUtil = securityUtil;

        this.userService = userService;
    }

    @PostMapping("/auth/login")
    @ApiMessage("Login thành công")
    public ResponseEntity<ResLoginDTO> login(@Valid @RequestBody ReqLoginDTO reqLoginDTO) {
        //Nạp input gồm username/password vào Security
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(reqLoginDTO.getUsername(), reqLoginDTO.getPassword());

        //xác thực người dùng => cần viết hàm loadUserByUsername
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        //set thông tin người dùng đăng nhập vào context (có thể sử dụng sau này)
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ResLoginDTO res = new ResLoginDTO();

        User currentUserDB = userService.handleGetUserByUsername(reqLoginDTO.getUsername());
        if (currentUserDB != null) {
            ResLoginDTO.UserLogin userLogin = new ResLoginDTO.UserLogin(
                    currentUserDB.getId(),
                    currentUserDB.getUsername(),
                    currentUserDB.getFirstName(),
                    currentUserDB.getLastName(),
                    currentUserDB.getPhoneNumber(),
                    currentUserDB.getEmail(),
                    currentUserDB.getDateOfBirth(),
                    currentUserDB.getRole()
            );
            res.setUser(userLogin);
        }

        String access_token = this.securityUtil.createAccessToken(authentication.getName(), res.getUser());
        res.setAccessToken(access_token);


        //create refresh token
        String refresh_token = this.securityUtil.createRefreshToken(reqLoginDTO.getUsername(), res);

        //update user
        this.userService.updateUserToken(refresh_token, reqLoginDTO.getUsername());

        //set cookies
        ResponseCookie resCookie = ResponseCookie.from("refresh_token", refresh_token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(refreshTokenExpiration)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, resCookie.toString())
                .body(res);
    }

    @GetMapping("/auth/account")
    @ApiMessage("Lấy thông tin tài khoản")
    public ResponseEntity<ResLoginDTO.UserLogin> getAccount() {
        String username = SecurityUtil.getCurrentUserLogin().isPresent()
                ? SecurityUtil.getCurrentUserLogin().get() :
                "";
        User currentUserDB = userService.handleGetUserByUsername(username);
        ResLoginDTO.UserLogin userLogin = new ResLoginDTO.UserLogin();
        if (currentUserDB != null) {
            userLogin.setId(currentUserDB.getId());
            userLogin.setUsername(currentUserDB.getUsername());
            userLogin.setFirstName(currentUserDB.getFirstName());
            userLogin.setLastName(currentUserDB.getLastName());
            userLogin.setPhoneNumber(currentUserDB.getPhoneNumber());
            userLogin.setEmail(currentUserDB.getEmail());
            userLogin.setDateOfBirth(currentUserDB.getDateOfBirth());
            userLogin.setRole(currentUserDB.getRole());
        }
        return ResponseEntity.ok().body(userLogin);
    }

    @GetMapping("/auth/refresh")
    @ApiMessage("Get User by refresh token")
    public ResponseEntity<ResLoginDTO> getUserByRefreshToken(
            @CookieValue(name = "refresh_token", defaultValue = "abc") String refreshToken
    ) throws IdInvalidException {
        if (refreshToken.equals("abc")) {
            throw new IdInvalidException("Bạn không có refresh token ở cookies");
        }

        // Check valid
        Jwt decodedToken = this.securityUtil.checkValidRefreshToken(refreshToken);
        String username = decodedToken.getSubject();

        // Check user by token + username
        User currentUser = this.userService.getUserByRefreshTokenAndUsername(refreshToken, username);
        if (currentUser == null) {
            throw new IdInvalidException("Refresh token không hợp lệ");
        }

        // Issue new token/set refresh token as cookies
        ResLoginDTO res = new ResLoginDTO();

        User currentUserDB = userService.handleGetUserByUsername(username);
        if (currentUserDB != null) {
            ResLoginDTO.UserLogin userLogin = new ResLoginDTO.UserLogin(
                    currentUserDB.getId(),
                    currentUserDB.getUsername(),
                    currentUserDB.getFirstName(),
                    currentUserDB.getLastName(),
                    currentUserDB.getPhoneNumber(),
                    currentUserDB.getEmail(),
                    currentUserDB.getDateOfBirth(),
                    currentUserDB.getRole()
            );
            res.setUser(userLogin);
        }

        String access_token = this.securityUtil.createAccessToken(username, res.getUser());
        res.setAccessToken(access_token);

        // Create new refresh token
        String new_refresh_token = this.securityUtil.createRefreshToken(username, res);

        // Update user
        this.userService.updateUserToken(new_refresh_token, username);

        // Set cookies
        ResponseCookie resCookie = ResponseCookie.from("refresh_token", new_refresh_token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(refreshTokenExpiration)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, resCookie.toString())
                .body(res);
    }

    @PostMapping("/auth/logout")
    @ApiMessage("Logout User Successfully")
    public ResponseEntity<Void> logout() throws IdInvalidException {
        String username = SecurityUtil.getCurrentUserLogin().isPresent() ? SecurityUtil.getCurrentUserLogin().get() : "";
        if(username.equals("")) {
            throw new IdInvalidException("Access token không hợp lệ");
        }

        //update refresh token = null
        this.userService.updateUserToken(null, username);

        //remove refresh token cookie
        ResponseCookie resCookie = ResponseCookie.from("refresh_token", null)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0) // Set max age to 0 to delete the cookie
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, resCookie.toString())
                .build();
    }
}
