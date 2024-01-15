package com.topstudy.com.topstudy.service;

import com.topstudy.com.topstudy.dto.PaymentDTO;
import com.topstudy.com.topstudy.model.Payment;
import com.topstudy.com.topstudy.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public Payment initiatePayment(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(new Date());
        return paymentRepository.save(payment);
    }

    @Transactional
    public Payment confirmPayment(Long paymentId) {
        Payment confirmedPayment = paymentRepository.findById(paymentId).orElse(null);

        if (confirmedPayment != null) {
            // Perform payment confirmation logic here
            // For example, you might update the payment status to "CONFIRMED"
            // confirmedPayment.setStatus(PaymentStatus.CONFIRMED);
            return paymentRepository.save(confirmedPayment);
        }

        return null; // Return null if the payment with the specified ID is not found
    }
}
