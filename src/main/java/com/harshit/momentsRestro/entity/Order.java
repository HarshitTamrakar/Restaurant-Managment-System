package com.harshit.momentsRestro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class Order {

    public enum Status {
        PLACED , COMPLETED, CANCELLED
    }

    @Id
    private long id;
    private List<OrderItem> orderItemList;
    private Date createdAt;
    private double totalAmount;
    private String status;
    private Payment payment;
    private DeliveryBoy deliveryBoy;

    public void calculateAmount(){
        double amount = 0.0;
        for(OrderItem item : this.getOrderItemList()){
            int quantity = item.getQuantity();
            double rate = item.getAmount();
            amount += (quantity * rate);
        }
        this.totalAmount = amount;
    }

}
