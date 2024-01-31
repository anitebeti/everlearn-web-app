package com.everlearn.everlearnwebapp.repository;

import com.everlearn.everlearnwebapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //TODO find by roles si altele
    User findByUsername(String name);

}
