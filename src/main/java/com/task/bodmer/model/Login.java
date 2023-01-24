package com.task.bodmer.model;

import com.task.bodmer.Validation.Username;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Login {
    public interface LoginByUsername {
    }

    public interface LoginByEmail {
    }

    @Username
    private String username;
    @Email
    private String email;
    @NotNull
    private String password;
}
