package com.topstudy.com.topstudy.dto;

import lombok.Data;

@Data

public class PaymentDTO {

    private String paymentMethod;
    private Double amount;
    private  String description;


    // Constructors, getters, and setters
}
