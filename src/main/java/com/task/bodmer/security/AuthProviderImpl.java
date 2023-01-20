package com.task.bodmer.security;

import com.task.bodmer.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final UserServiceImpl userDetServiceImpl;
    private final PasswordEncoder passwordEncoder;

    public AuthProviderImpl(UserServiceImpl userDetServiceImpl, PasswordEncoder passwordEncoder) {
        this.userDetServiceImpl = userDetServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        UserDetails userDetails = userDetServiceImpl.loadUser(userName);
        String password = passwordEncoder.encode(authentication.getCredentials().toString());
        if (!password.equals(userDetails.getPassword()))
            throw new BadCredentialsException("The password is incorrect");
        return new UsernamePasswordAuthenticationToken(userDetails, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
x`
}
