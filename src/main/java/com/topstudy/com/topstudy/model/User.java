package com.topstudy.com.topstudy.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String staffId;

    @Column(nullable = false)
    private String password;


    @Column(nullable = false)
    private Boolean  status;

    @OneToMany(mappedBy = "user")
    private List<Solution> solutions;  // List of solutions associated with this user



}
