package com.everlearn.everlearnwebapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="sessions")
@Data
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @Column
    private LocalDateTime startTime;

//    //TODO: does this make sense?
//    @Column
//    private String userDevice;
//    //TODO: vezi daca ai voie sa o stochezi
//    @Column
//    private String ipAddress;
}
