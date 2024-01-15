package com.topstudy.com.topstudy.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "client_accounts")
@Data
public class ClientAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "clientAccount")
    private List<Task> tasks;

    @OneToMany(mappedBy = "clientAccount")
    private List<Payment> payments;  // List of payments associated with this client account


    @OneToMany(mappedBy = "clientAccount")
    private List<Solution> solutions;





    // Other client account properties (e.g., address, phone number)

    // Constructors, getters, and setters
}
