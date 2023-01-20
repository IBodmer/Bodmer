package com.task.bodmer.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.task.bodmer.dto.UserDTO;
import com.task.bodmer.model.Users;
import com.task.bodmer.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("api/v1/registration")
public class RegistrationController {
    private final UserServiceImpl userService;

    @Autowired
    public RegistrationController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Validated(Users.New.class)
    @JsonView(Users.Exist.class)
    @PostMapping
    public ResponseEntity<UserDTO> registrationUser(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.registrationUser(userDTO));
    }
}
