package com.user.UserService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Users")
public class Users {
    @Id
    @UuidGenerator
    @Column(name = "ID")
    private String id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ABOUT")
    private String about;
    @Transient
    private List<Ratings> ratings = new ArrayList<>();
}