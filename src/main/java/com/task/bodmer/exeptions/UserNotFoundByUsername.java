package com.task.bodmer.exeptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotFoundByUsername extends UsernameNotFoundException {
    public UserNotFoundByUsername(String msg) {
        super(msg);
    }
}
