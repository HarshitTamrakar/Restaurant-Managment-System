package com.harshit.momentsRestro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orderItems")
public class OrderItem {

    @Id
    private long id;
    private long orderId;
    private MenuItem menuItem;
    private int quantity;
    private long amount;

    public long calculateAmount() {
        return this.getQuantity() * this.getMenuItem().getRate();
    }

}
