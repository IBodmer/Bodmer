package com.task.bodmer.model;

import com.task.bodmer.Validation.Username;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Login {
    public interface LoginByUsername{}
    public interface LoginByEmail{}
    @Username
    private String username;
    @Email
    private String email;
}
