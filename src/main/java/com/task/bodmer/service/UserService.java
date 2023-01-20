package com.task.bodmer.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails loadByEmail(String emailOrName);
    UserDetails loadByUsername(String emailOrName);
}
