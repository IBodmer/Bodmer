package com.task.bodmer.security.exeptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotFound extends UsernameNotFoundException {
    public UserNotFound(String msg) {
        super(msg);
    }
}
