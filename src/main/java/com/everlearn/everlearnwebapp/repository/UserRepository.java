package com.everlearn.everlearnwebapp.repository;

import com.everlearn.everlearnwebapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //TODO find by roles si altele
    Optional<User> findByEmail(String email);

}
