package com.task.bodmer.mapper;

import com.task.bodmer.dto.UserDTO;
import com.task.bodmer.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "id", ignore = true)
    UserDTO toDTO(Users users);


    @Mapping(source = "password", target = "password", qualifiedByName = "bcrypt")
    Users toUser(UserDTO userDTO);

    @Named("bcrypt")
    default String cryptPass(String pass) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(pass);
    }
}
