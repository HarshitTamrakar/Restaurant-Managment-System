package com.harshit.momentsRestro.controller;

import com.harshit.momentsRestro.entity.DeliveryBoy;
import com.harshit.momentsRestro.entity.Order;
import com.harshit.momentsRestro.exception.DeliveryBoyNotFoundException;
import com.harshit.momentsRestro.exception.OrderAlreadyPaidException;
import com.harshit.momentsRestro.exception.OrderNotFoundException;
import com.harshit.momentsRestro.service.DeliveryBoySerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery-boy")
public class DeliveryBoyController {

    @Autowired
    DeliveryBoySerivce deliveryBoySerivce;

    @PostMapping
    public DeliveryBoy createDeliveryBoy(@RequestBody DeliveryBoy deliveryBoy) {
        return deliveryBoySerivce.createDeliveryBoy(deliveryBoy);
    }

    @GetMapping("/{id}")
    public DeliveryBoy getDeliveryBoy(@PathVariable("id") long deliveryBoyId) throws DeliveryBoyNotFoundException {
        return deliveryBoySerivce.getDeliveryBoy(deliveryBoyId);
    }

    @GetMapping
    public List<DeliveryBoy> getDeliveryBoyList() {
        return deliveryBoySerivce.getDeliveryBoyList();
    }

    @DeleteMapping("/{id}")
    public void deleteDeliveryBoy(@PathVariable("id") long deliveryBoyId){
        deliveryBoySerivce.deleteDeliveryBoy(deliveryBoyId);
    }

    @PostMapping("/{id}/deliver-order/{orderId}")
    public Order deliverOrder(@PathVariable("id") long deliveryBoyId, @PathVariable("orderId") long orderId) throws OrderNotFoundException, DeliveryBoyNotFoundException, OrderAlreadyPaidException {
        return deliveryBoySerivce.deliverOrder(deliveryBoyId, orderId);
    }

}
