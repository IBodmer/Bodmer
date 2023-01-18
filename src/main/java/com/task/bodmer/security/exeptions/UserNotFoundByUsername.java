package com.task.bodmer.security.exeptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotFoundByUsername extends UsernameNotFoundException {
    public UserNotFoundByUsername(String msg) {
        super(msg);
    }
}
