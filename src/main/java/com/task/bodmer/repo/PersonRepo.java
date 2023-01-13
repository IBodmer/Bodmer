package com.task.bodmer.repo;

import com.task.bodmer.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepo extends JpaRepository<Person,Long> {
    Optional<Person> findByUserName (String userName);
}
