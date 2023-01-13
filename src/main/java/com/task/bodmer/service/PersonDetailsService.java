package com.task.bodmer.service;

import com.task.bodmer.exeptions.UserNotFoundByUsername;
import com.task.bodmer.model.Person;
import com.task.bodmer.repo.PersonRepo;
import com.task.bodmer.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PersonRepo personRepo;

    @Autowired
    public PersonDetailsService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Person person = personRepo.findByUserName(username).orElseThrow(() -> new UserNotFoundByUsername("Пользователь " + username + " не найден"));
        return new PersonDetails(person);
    }
}
