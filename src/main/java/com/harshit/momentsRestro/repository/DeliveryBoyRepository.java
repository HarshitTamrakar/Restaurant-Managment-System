package com.harshit.momentsRestro.repository;

import com.harshit.momentsRestro.entity.DeliveryBoy;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryBoyRepository extends MongoRepository<DeliveryBoy, Long> {
}
