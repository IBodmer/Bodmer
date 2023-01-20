package com.task.bodmer.service;

import com.task.bodmer.dto.UserDTO;
import com.task.bodmer.mapper.UserMapper;
import com.task.bodmer.model.Users;
import com.task.bodmer.repo.UserRepo;
import com.task.bodmer.security.UserDet;
import com.task.bodmer.security.exeptions.UserNotFound;
import com.task.bodmer.utils.PatternUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.regex.Matcher;

@Service
@Validated
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional
    public UserDTO registrationUser(UserDTO userDTO) {
        userRepo.findByUserName(userDTO.getUserName()).orElseThrow(() -> new UserNotFound("Такой пользователь уже зарегестрирован"));
        return UserMapper.INSTANCE.toDTO(userRepo.save(UserMapper.INSTANCE.toUser(userDTO)));
    }


    @Override
    public UserDetails loadByEmail(String emailOrName) {
        Users users = userRepo.findByEmail(emailOrName).orElseThrow(() -> new UserNotFound("The email11 or password is incorrect."));
        return new UserDet(users);
    }

    @Override
    public UserDetails loadByUsername(String emailOrName) {
        Users users = userRepo.findByUserName(emailOrName).orElseThrow(() -> new UserNotFound("The email22 or password is incorrect."));
        return new UserDet(users);
    }

    public boolean validate(String emailOrName) {
        Matcher matcher = PatternUtils.VALID_EMAIL.matcher(emailOrName);
        return matcher.find();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //пришлось хитрить, чтобы секьюрити понимал че делать. имя дерьмо, но работает
        if (validate(username)) {
            return loadByEmail(username);
        } else {
            return loadByUsername(username);
        }
    }
}
