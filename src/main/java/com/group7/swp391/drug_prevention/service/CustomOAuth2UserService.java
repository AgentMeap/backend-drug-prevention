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
        String avatarUrl = oauth2User.getAttribute("picture");

        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(email));

        User user;
        if (userOptional.isPresent() && userOptional.get() != null) {
            user = userOptional.get();
            // Update existing user's info
            user.setFirstName(oauth2User.getAttribute("given_name"));
            user.setLastName(oauth2User.getAttribute("family_name"));
            user.setAvatar(avatarUrl);
        } else {
            user = new User();
            user.setUsername(email);

            user.setEmail(email);
            user.setFirstName(oauth2User.getAttribute("given_name"));
            user.setLastName(oauth2User.getAttribute("family_name"));
            user.setAvatar(avatarUrl);
            user.setPassword(this.passwordEncoder.encode("123456"));
            user.setRole(RoleEnum.MEMBER);
        }
        userRepository.saveAndFlush(user);
        return oauth2User;
    }
}