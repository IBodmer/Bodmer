package com.task.bodmer.dto;

import com.task.bodmer.Validation.Username;
import com.task.bodmer.model.Login;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class LoginDTO {
    @Null(groups = {Login.LoginByEmail.class})
    @NotNull(groups = {Login.LoginByUsername.class})
    @Username
    private String username;
    @Null(groups = {Login.LoginByUsername.class})
    @NotNull(groups = {Login.LoginByEmail.class})
    @Email
    private String email;
    @NotNull
    private String password;
}
