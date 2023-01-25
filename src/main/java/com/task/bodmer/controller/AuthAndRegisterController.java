package com.task.bodmer.controller;

import com.task.bodmer.dto.AuthResponse;
import com.task.bodmer.dto.LoginDTO;
import com.task.bodmer.dto.UserDTO;
import com.task.bodmer.model.Login;
import com.task.bodmer.model.Users;
import com.task.bodmer.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(value = "api/v1/auth")
public class AuthAndRegisterController {
    private final AuthService authService;

    public AuthAndRegisterController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/registration")
    @Validated(Users.New.class)
    public ResponseEntity<AuthResponse> registrationUser(@Valid @RequestBody UserDTO userDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(authService.registrationUser(userDTO));
    }

    @PostMapping("/authenticate")
    @Validated(Login.LoginByUsername.class)
    public ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.authenticate(loginDTO));
    }
}
