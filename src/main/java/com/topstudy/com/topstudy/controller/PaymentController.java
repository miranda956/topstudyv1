package com.topstudy.com.topstudy.controller;

import com.topstudy.com.topstudy.dto.PaymentDTO;
import com.topstudy.com.topstudy.model.Payment;
import com.topstudy.com.topstudy.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService; // You'll need to create this service

    // Initiate a payment
    @PostMapping
    public ResponseEntity<Payment> initiatePayment(@RequestBody PaymentDTO paymentDTO) {
        Payment initiatedPayment = paymentService.initiatePayment(paymentDTO);
        return new ResponseEntity<>(initiatedPayment, HttpStatus.CREATED);
    }

    // Confirm payment
    @PostMapping("/{payment_id}/confirm")
    public ResponseEntity<Payment> confirmPayment(@PathVariable("payment_id") Long paymentId) {
        Payment confirmedPayment = paymentService.confirmPayment(paymentId);
        if (confirmedPayment != null) {
            return new ResponseEntity<>(confirmedPayment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
