package com.task.bodmer.dto;

import com.task.bodmer.Validation.Username;
import com.task.bodmer.model.Login;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
