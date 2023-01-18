package com.task.bodmer.security;

import com.task.bodmer.service.UserDetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final UserDetService userDetService;

    @Autowired
    public AuthProviderImpl(UserDetService userDetService) {
        this.userDetService = userDetService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        UserDetails userDetails = userDetService.loadUserByUsername(userName);
        String password = authentication.getCredentials().toString();
        if (!password.equals(userDetails.getPassword())) throw new BadCredentialsException(""); //todo username + password написать
        return new UsernamePasswordAuthenticationToken(userDetails, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
