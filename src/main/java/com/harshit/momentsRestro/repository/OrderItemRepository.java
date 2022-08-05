package com.harshit.momentsRestro.repository;

import com.harshit.momentsRestro.entity.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends MongoRepository<OrderItem, Long> {

    public List<OrderItem> findByOrderId(long orderId);

}
