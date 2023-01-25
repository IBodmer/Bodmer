package com.task.bodmer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {
    public interface New {
    }

    public interface Exist {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "user_name")
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    @Email
    @NotNull
    private String email;
    @Column(name = "password")
    @NotNull
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private ApplicationRole application_role;
    @Column(name = "registered_at")
    private LocalDateTime registered_at;
//TODO дописать поле с коллекцией проектов и задач, а так же добавить все в миграцию, поменять название ролей в проекте

}
