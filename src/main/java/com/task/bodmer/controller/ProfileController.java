package com.task.bodmer.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Validated
@RestController
@RequestMapping("api/v1/profile")
public class ProfileController {
    @GetMapping
    public String sayHello(){
        return "hello";
    }
}
