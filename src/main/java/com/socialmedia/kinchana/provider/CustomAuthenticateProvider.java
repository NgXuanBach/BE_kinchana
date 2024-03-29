package com.socialmedia.kinchana.provider;

import com.socialmedia.kinchana.entity.UserEntity;
import com.socialmedia.kinchana.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@Configuration
public class CustomAuthenticateProvider  implements AuthenticationProvider {
    private Logger logger = LoggerFactory.getLogger(CustomAuthenticateProvider.class);
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserEntity user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(email, user.getPassword(), new ArrayList<>());
        } else {
            throw new BadCredentialsException("The password's incorrect or the user doesn't exist.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //khai báo loại chứng thực sử dụng để so sánh
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
