package com.harshit.momentsRestro.service;

import com.harshit.momentsRestro.entity.Payment;
import com.harshit.momentsRestro.exception.PaymentNotFoundException;
import com.harshit.momentsRestro.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment getPayment(long paymentId) throws PaymentNotFoundException {
        Payment payment = paymentRepository.findById(paymentId).orElse(null);
        if (payment == null) {
            throw new PaymentNotFoundException("Payment not found");
        }
        return payment;
    }

}
