package com.everlearn.everlearnwebapp.repository;

import com.everlearn.everlearnwebapp.entity.Photo;
import com.everlearn.everlearnwebapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRespository extends JpaRepository<Photo, Long> {

    Photo findByUser(User user);
}
