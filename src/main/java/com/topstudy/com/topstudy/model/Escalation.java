package com.topstudy.com.topstudy.model;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Entity
@Table(name = "escalations")
@Data
public class Escalation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;


    @Column(nullable = false)
    private String requestReason;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date requestDate;

    @Column(nullable = false)
    private String description;



    // Constructors, getters, and setters
}
