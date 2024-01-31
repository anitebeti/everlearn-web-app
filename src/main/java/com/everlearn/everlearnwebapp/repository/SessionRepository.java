package com.everlearn.everlearnwebapp.repository;

import com.everlearn.everlearnwebapp.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

}
