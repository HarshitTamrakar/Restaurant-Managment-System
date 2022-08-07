package com.harshit.momentsRestro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "payment")
public class Payment {

    enum PaymentModes {
        COD, PREPAID
    }

    private String paymentMode;
    private String transactionId;
    private double amount;

}
