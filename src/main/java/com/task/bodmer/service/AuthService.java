package com.task.bodmer.service;

import com.task.bodmer.dto.AuthResponse;
import com.task.bodmer.dto.LoginDTO;
import com.task.bodmer.dto.UserDTO;
import com.task.bodmer.mapper.UserMapper;
import com.task.bodmer.model.ApplicationRole;
import com.task.bodmer.model.Users;
import com.task.bodmer.repo.UserRepo;
import com.task.bodmer.security.UserDetails_;
import com.task.bodmer.security.exeptions.UserNotFound;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class AuthService {
    private final UserRepo userRepo;

    private final JWTService jwtService;
    private  final AuthenticationManager authenticationManager;

    public AuthService(UserRepo userRepo, JWTService jwtService, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public AuthResponse registrationUser(UserDTO userDTO) {
        userRepo.findByUserName(userDTO.getUsername()).orElseThrow(() -> new UserNotFound("Такой пользователь уже зарегестрирован"));
        Users users = UserMapper.INSTANCE.toUser(userDTO);
        users.setApplicationRole(ApplicationRole.USER);
        Users save = userRepo.save(users);
        String jwtToken = jwtService.generateToken(new UserDetails_(save));
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticate(LoginDTO loginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );
        var user = userRepo.findByEmail(loginDTO.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(new UserDetails_(user));
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

}
