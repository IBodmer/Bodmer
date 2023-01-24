package com.task.bodmer.mapper;

import com.task.bodmer.dto.UserDTO;
import com.task.bodmer.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "id", ignore = true)
    UserDTO toDTO(Users users);


    @Mapping(source = "password", target = "password")
    Users toUser(UserDTO userDTO);

}
