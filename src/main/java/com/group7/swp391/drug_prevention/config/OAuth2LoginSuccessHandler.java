package com.group7.swp391.drug_prevention.config;

import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.domain.response.ResLoginDTO;
import com.group7.swp391.drug_prevention.service.UserService;
import com.group7.swp391.drug_prevention.util.SecurityUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final SecurityUtil securityUtil;
    private final UserService userService;

    @Value("${project.jwt.refresh-token-validity-in-seconds}")
    private long refreshTokenExpiration;

    @Value("${project.frontend.url}")
    private String frontendUrl;

    public OAuth2LoginSuccessHandler(SecurityUtil securityUtil, UserService userService) {
        this.securityUtil = securityUtil;
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        User currentUserDB = userService.handleGetUserByUsername(email);

        if (currentUserDB == null) {
            throw new RuntimeException("User not found in database after OAuth2 login. Email: " + email);
        }

        String dbUsername = currentUserDB.getUsername();
        // Create the DTO to be used for token generation
        ResLoginDTO res = new ResLoginDTO();
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

        // Create Access and Refresh Tokens using the database username
        String accessToken = this.securityUtil.createAccessToken(dbUsername, res.getUser());
        String refreshToken = this.securityUtil.createRefreshToken(dbUsername, res);
        this.userService.updateUserToken(refreshToken, dbUsername);

        // Set Refresh Token as a cookie
        ResponseCookie resCookie = ResponseCookie.from("refresh_token", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(refreshTokenExpiration)
                .build();
        response.addHeader("Set-Cookie", resCookie.toString());

        // Redirect to frontend with the access token
        String redirectUrl = UriComponentsBuilder.fromUriString(frontendUrl)
                .queryParam("token", accessToken)
                .build().toUriString();

        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
