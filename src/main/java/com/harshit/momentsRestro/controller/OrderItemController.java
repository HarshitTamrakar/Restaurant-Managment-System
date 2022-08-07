package com.harshit.momentsRestro.controller;

import com.harshit.momentsRestro.entity.OrderItem;
import com.harshit.momentsRestro.exception.OrderItemNotFoundException;
import com.harshit.momentsRestro.exception.OrderNotFoundException;
import com.harshit.momentsRestro.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderItemController {

    @Autowired
    OrderItemService orderItemService;

    @PostMapping("/{id}/order-item")
    public OrderItem createOrderItem(@PathVariable("id") long orderId, @RequestBody OrderItem orderItem) throws OrderNotFoundException {
        return orderItemService.createOrderItem(orderId, orderItem);
    }

    @GetMapping("/{id}")
    public List<OrderItem> getOrderItems(@PathVariable("id") long orderId) throws OrderNotFoundException {
        return orderItemService.getOrderItems(orderId);
    }

    @DeleteMapping("/{orderId}/order-item/{itemId}")
    public boolean deleteOrderItem(@PathVariable("orderId") long orderId, @PathVariable("itemId") long orderItemId) throws OrderItemNotFoundException, OrderNotFoundException {
        return orderItemService.deleteOrderItem(orderId, orderItemId);
    }

    @PutMapping("/{orderId}/order-item/{itemId}")
    public OrderItem updateOrderItem(@PathVariable("orderId") long orderId, @PathVariable("itemId") long orderItemId, @RequestBody OrderItem updatedOrderItem) throws OrderItemNotFoundException, OrderNotFoundException {
        return orderItemService.updateOrderItem(orderId, orderItemId, updatedOrderItem);
    }

}
