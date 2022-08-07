package com.harshit.momentsRestro.controller;

import com.harshit.momentsRestro.entity.Order;
import com.harshit.momentsRestro.entity.Payment;
import com.harshit.momentsRestro.exception.InvalidPaymentException;
import com.harshit.momentsRestro.exception.OrderNotFoundException;
import com.harshit.momentsRestro.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable("id") long orderId) throws OrderNotFoundException {
        return orderService.getOrder(orderId);
    }

    @PostMapping("/cancel-order/{id}")
    public Order cancelOrder(@PathVariable("id") long orderId) throws OrderNotFoundException{
        return orderService.cancelOrder(orderId);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") long orderId){
        orderService.deleteOrder(orderId);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable("id") long orderId, @RequestBody Order updatedOrder) throws OrderNotFoundException {
        return orderService.updateOrder(orderId, updatedOrder);
    }

    @PostMapping("/{id}")
    public Order payOrder(@PathVariable("id") long orderId, @RequestBody Payment payment) throws OrderNotFoundException, InvalidPaymentException {
        return orderService.payOrder(orderId, payment);
    }

}
