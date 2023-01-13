package com.task.bodmer.dto;

import com.task.bodmer.model.Role;
import com.task.bodmer.model.RoleProject;
import com.task.bodmer.model.User;
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
    @Null(groups = {User.New.class})
    private Long id;

    @NotNull(groups = {User.New.class})
    private String userName;

    @Null(groups = {User.New.class})
    private String name;

    @Null(groups = {User.New.class})
    private String lastName;

    @NotNull(groups = {User.New.class})
    @Email(groups = {User.New.class})
    private String email;

    @NotNull(groups = {User.New.class})
    private String password;

    @Null(groups = {User.New.class})
    private Role role;

    @Null(groups = {User.New.class})
    private RoleProject roleProject;

    @Null(groups = {User.New.class})
    private LocalDateTime created;

}
