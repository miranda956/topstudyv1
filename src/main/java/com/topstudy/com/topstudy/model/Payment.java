package com.topstudy.com.topstudy.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "payments")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String transactionId;

    @Column(nullable = false)
    private String paymentLink;


    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private Double amount;

    @Column(name = "payment_description")
    private String description;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date paymentDate;


    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task; // Reference to the associated task

    @ManyToOne
    @JoinColumn(name = "client_account_id", nullable = false)
    private ClientAccount clientAccount; // Reference to the associated client account




    // Constructors, getters, and setters
}
