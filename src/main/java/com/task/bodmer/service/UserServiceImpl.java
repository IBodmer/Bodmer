package com.task.bodmer.service;

import com.task.bodmer.model.Users;
import com.task.bodmer.repo.UserRepo;
import com.task.bodmer.security.UserDetails_;
import com.task.bodmer.security.exeptions.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UserServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;


    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        Users users = userRepo.findByUsername(username).orElseThrow(() -> new UserNotFound("The email22 or password is incorrect."));
        return new UserDetails_(users);
    }

}
