package com.topstudy.com.topstudy.model;

import com.topstudy.com.topstudy.enums.SolutionStatus;
import com.topstudy.com.topstudy.model.Task;
import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Entity
@Table(name = "solutions")
@Data
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Reference to the associated user who worked on the solution

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date submissionDate;



    @Enumerated(EnumType.STRING)
    private SolutionStatus status;

    @Column(name = "document_url")
    private String documentUrl;

    @ManyToOne
    @JoinColumn(name = "client_account_id", nullable = false)
    private ClientAccount clientAccount; // Reference to the associated client account who solution is for





    // Constructors, getters, and setters
}
