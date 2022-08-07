package com.harshit.momentsRestro.repository;

import com.harshit.momentsRestro.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, Long> {
}
