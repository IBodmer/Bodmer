package com.task.bodmer.dto;

import com.task.bodmer.Validation.Username;
import com.task.bodmer.model.ApplicationRole;
import com.task.bodmer.model.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserDTO {
    @Null(groups = {Users.New.class,Users.Exist.class})
    private Long id;

    @NotNull
    @Username
    private String userName;

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
