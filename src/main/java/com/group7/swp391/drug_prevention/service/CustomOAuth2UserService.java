package com.group7.swp391.drug_prevention.service;

import com.group7.swp391.drug_prevention.domain.User;
import com.group7.swp391.drug_prevention.repository.UserRepository;
import com.group7.swp391.drug_prevention.util.constant.RoleEnum;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public CustomOAuth2UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");

        // FIX: Wrap the result in Optional.ofNullable() to handle a plain User object
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(email));

        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            // Optionally update user details
            user.setFirstName(oauth2User.getAttribute("given_name"));
            user.setLastName(oauth2User.getAttribute("family_name"));
        } else {
            // Create a new user if they don't exist
            user = new User();
            user.setUsername(email); // Using email as username
            user.setEmail(email);
            user.setFirstName(oauth2User.getAttribute("given_name"));
            user.setLastName(oauth2User.getAttribute("family_name"));
            String randomPassword = UUID.randomUUID().toString();
            user.setPassword(this.passwordEncoder.encode(randomPassword));
            user.setRole(RoleEnum.valueOf("MEMBER"));// Default role
        }
        userRepository.save(user);

        return oauth2User;
    }
}