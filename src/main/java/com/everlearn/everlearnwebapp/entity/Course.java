package com.everlearn.everlearnwebapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message = "Course name must not be null")
    @NotBlank(message = "Course name must not be blank")
    private String courseName;

    @Column
    private String description;
    
    @Column
    private String type;

    @Lob
    private byte[] data;

    @ManyToMany(mappedBy = "subscribedCourses")
    private Set<User> users;

    @ManyToOne(fetch = FetchType.EAGER)
//            (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
//    @NotNull(message = "Author must not be null")
//    @NotBlank(message = "Author name must not be blank")
    private User author;


    public Course() {}

    public Course(String courseName, String description, String type, byte[] data, User author) {
        this.courseName = courseName;
        this.description = description;
        this.type = type;
        this.data = data;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
