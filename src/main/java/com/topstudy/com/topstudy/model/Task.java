package com.topstudy.com.topstudy.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.*;
import java.util.Objects;
import java.time.temporal.ChronoUnit;


import java.math.BigDecimal;

@Entity
@Table(name = "tasks")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String taskId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(name = "document_url")
    private String documentUrl;

    @Column(name ="word_count")
    private String wordCount;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false)
    private String budget;
    @Column(name = "created_at", nullable = false)
    private Instant createdAt; // Assuming you want the creation timestamp in UTC

    @Column(name ="payment_link")
    private String paymentLink;

    @Column(nullable = false)
    private Boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "client_account_id", nullable = false)
    private ClientAccount clientAccount; // Reference to the associated client account



    public Task() {
        this.createdAt = Instant.now();
    }

    @Transient
    private String remainingTime; // Transient field for dynamic calculation
    public void calculateRemainingTime() {
        if (duration == null ) {
            remainingTime = "Not Set";
            return;
        }

        try {
            int durationInHours = duration;

            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime endTime = LocalDateTime.ofInstant(createdAt, ZoneId.systemDefault()).plusHours(durationInHours);

            if (endTime.isBefore(currentTime)) {
                remainingTime = "Task Overdue";
            } else {
                Duration duration = Duration.between(currentTime, endTime);
                long remainingHours = duration.toHours();
                long remainingMinutes = duration.minusHours(remainingHours).toMinutes();
                long remainingSeconds = duration.minusHours(remainingHours).minusMinutes(remainingMinutes).getSeconds();

                remainingTime = String.format("%d hours %d minutes %d seconds", remainingHours, remainingMinutes, remainingSeconds);
            }
        } catch (NumberFormatException e) {
            remainingTime = "Invalid Duration Format";
        }
    }

}






    // Other task properties (e.g., due date, priority)

    // Constructors, getters, and setters

