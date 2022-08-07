package com.harshit.momentsRestro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "deliveryBoy")
public class DeliveryBoy {

    @Id
    private long id;
    private String name;
    private int age;
    private String contactNo;
}
