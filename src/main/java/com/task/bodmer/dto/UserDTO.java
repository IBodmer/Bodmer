package com.task.bodmer.dto;

import com.task.bodmer.model.ApplicationRole;
import com.task.bodmer.model.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserDTO {
    @Null
    private Long id;

    @NotNull
    private String username;

    @Null(groups = {Users.New.class})
    private String name;

    @Null(groups = {Users.New.class})
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @Null(groups = {Users.New.class})
    @NotNull(groups = Users.Exist.class)
    private ApplicationRole applicationRole;

    @Null(groups = {Users.New.class})
    private LocalDateTime createdAt;

}
