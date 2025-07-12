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

    @Value("${project.frontend.url}") // Add this to your application.properties
    private String frontendUrl;

    public OAuth2LoginSuccessHandler(SecurityUtil securityUtil, UserService userService) {
        this.securityUtil = securityUtil;
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String username = oAuth2User.getAttribute("email"); // Using email as the unique identifier

        // Fetch the user from DB
        User currentUserDB = userService.handleGetUserByUsername(username);

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

        // Create Access Token
        String accessToken = this.securityUtil.createAccessToken(username, res.getUser());

        // Create Refresh Token
        String refreshToken = this.securityUtil.createRefreshToken(username, res);
        this.userService.updateUserToken(refreshToken, username);

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
