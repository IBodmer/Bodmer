package com.task.bodmer.service;

import com.task.bodmer.config.PasswordEncoder;
import com.task.bodmer.dto.AuthResponse;
import com.task.bodmer.dto.LoginDTO;
import com.task.bodmer.dto.UserDTO;
import com.task.bodmer.model.ApplicationRole;
import com.task.bodmer.model.Users;
import com.task.bodmer.repo.UserRepo;
import com.task.bodmer.security.UserDetails_;
import com.task.bodmer.security.exeptions.UserNotFound;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepo userRepo;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;



    public AuthService(UserRepo userRepo, JWTService jwtService, AuthenticationManager authenticationManager, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
    }


    public AuthResponse registrationUser(UserDTO userDTO) {
//        userRepo.findByUsername(userDTO.getUsername()).orElseThrow(() -> new UserNotFound("Такой пользователь уже зарегестрирован"));
        Users users = Users.builder()
                .email(userDTO.getEmail())
                .username(userDTO.getUsername())
                .password(encoder.bCryptPasswordEncoder().encode(userDTO.getPassword()))
                .application_role(ApplicationRole.USER)
                .build();
        Users save = userRepo.save(users);
        String jwtToken = jwtService.generateToken(new UserDetails_(save));
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticate(LoginDTO loginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );
        Users users = userRepo.findByUsername(loginDTO.getUsername()).orElseThrow(() -> new UserNotFound("Такой пользователь не зарегестрирован"));
        String jwtToken = jwtService.generateToken(new UserDetails_(users));
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

}
