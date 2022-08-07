package com.harshit.momentsRestro.controller;

import com.harshit.momentsRestro.entity.Payment;
import com.harshit.momentsRestro.exception.PaymentNotFoundException;
import com.harshit.momentsRestro.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable("id") long paymentId) throws PaymentNotFoundException {
        return paymentService.getPayment(paymentId);
    }

}
