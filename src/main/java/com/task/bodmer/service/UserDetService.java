package com.task.bodmer.service;

import com.task.bodmer.model.Users;
import com.task.bodmer.security.exeptions.UserNotFoundByUsername;
import com.task.bodmer.repo.UserRepo;
import com.task.bodmer.security.UserDet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetService implements UserDetailsService {
    private final UserRepo userRepo;

    @Autowired
    public UserDetService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Users users = userRepo.findByUserName(username).orElseThrow(() -> new UserNotFoundByUsername("Пользователь " + username + " не найден"));
        return new UserDet(users);
    }
}
