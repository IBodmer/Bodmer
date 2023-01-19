package com.task.bodmer.service;

import com.task.bodmer.model.Users;
import com.task.bodmer.security.exeptions.UserNotFound;
import com.task.bodmer.repo.UserRepo;
import com.task.bodmer.security.UserDet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserDetService implements UserDetailsService {
    private final UserRepo userRepo;
    private static final Pattern VALID_EMAIL =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    public UserDetService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String constr) {

        if (!validate(constr)) {
            Users users = userRepo.findByUserName(constr).orElseThrow(() -> new UserNotFound("The email or password is incorrect."));
            return new UserDet(users);
        } else {
            Users users = userRepo.findByEmail(constr).orElseThrow(() -> new UserNotFound("The email or password is incorrect."));
            return new UserDet(users);
        }

    }

    private boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL.matcher(emailStr);
        return matcher.find();
    }
}
