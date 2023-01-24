package com.task.bodmer.service;

import com.task.bodmer.model.Users;
import com.task.bodmer.repo.UserRepo;
import com.task.bodmer.security.UserDetails_;
import com.task.bodmer.security.exeptions.UserNotFound;
import com.task.bodmer.utils.PatternUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
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



    @Override
    public UserDetails loadByEmail(String emailOrName) {
        Users users = userRepo.findByEmail(emailOrName).orElseThrow(() -> new UserNotFound("The email11 or password is incorrect."));
        return new UserDetails_(users);
    }

    @Override
    public UserDetails loadByUsername(String emailOrName) {
        Users users = userRepo.findByUserName(emailOrName).orElseThrow(() -> new UserNotFound("The email22 or password is incorrect."));
        return new UserDetails_(users);
    }

    public boolean validate(String emailOrName) {
        Matcher matcher = PatternUtils.VALID_EMAIL.matcher(emailOrName);
        return matcher.find();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //пришлось хитрить, чтобы секьюрити понимал че делать. имя дерьмо, но работает
        if (validate(username)) {
            return loadByEmail(username);
        } else {
            return loadByUsername(username);
        }
    }

}
