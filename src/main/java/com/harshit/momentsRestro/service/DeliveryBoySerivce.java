package com.harshit.momentsRestro.service;

import com.harshit.momentsRestro.entity.DeliveryBoy;
import com.harshit.momentsRestro.entity.Order;
import com.harshit.momentsRestro.exception.DeliveryBoyNotFoundException;
import com.harshit.momentsRestro.exception.OrderAlreadyPaidException;
import com.harshit.momentsRestro.exception.OrderNotFoundException;
import com.harshit.momentsRestro.repository.DeliveryBoyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryBoySerivce {

    @Autowired
    DeliveryBoyRepository deliveryBoyRepository;
    OrderService orderService;

    public DeliveryBoy createDeliveryBoy(DeliveryBoy deliveryBoy) {
        return deliveryBoyRepository.save(deliveryBoy);
    }

    public DeliveryBoy getDeliveryBoy(long deliveryBoyId) throws DeliveryBoyNotFoundException {
        DeliveryBoy deliveryBoy = deliveryBoyRepository.findById(deliveryBoyId).orElse(null);
        if (deliveryBoy == null) throw new DeliveryBoyNotFoundException("Delivery Boy not found");
        return deliveryBoy;
    }

    public List<DeliveryBoy> getDeliveryBoyList() {
        return deliveryBoyRepository.findAll();
    }

    public void deleteDeliveryBoy(long id) {
        deliveryBoyRepository.deleteById(id);
    }

    public Order deliverOrder(long deliveryBoyId, long orderId) throws DeliveryBoyNotFoundException, OrderNotFoundException, OrderAlreadyPaidException {
        DeliveryBoy deliveryBoy = deliveryBoyRepository.findById(deliveryBoyId).orElse(null);
        if (deliveryBoy == null) throw new DeliveryBoyNotFoundException("Delivery boy not found");
        return orderService.deliverOrder(orderId, deliveryBoy);
    }

}
