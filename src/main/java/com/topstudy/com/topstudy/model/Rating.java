package com.topstudy.com.topstudy.model;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Entity
@Table(name = "ratings")
@Data
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "solution_id", nullable = false)
    private Solution solution;

    @Column(nullable = false)
    private int ratingValue;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date ratingDate;

    // Constructors, getters, and setters
}
