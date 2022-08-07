package com.harshit.momentsRestro.service;

import com.harshit.momentsRestro.entity.OrderItem;
import com.harshit.momentsRestro.exception.OrderItemNotFoundException;
import com.harshit.momentsRestro.exception.OrderNotFoundException;
import com.harshit.momentsRestro.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;
    OrderService orderService;

    public OrderItem createOrderItem(long orderId, OrderItem orderItem) throws OrderNotFoundException {
        OrderItem new_orderItem = orderItemRepository.save(orderItem);
        orderService.addOrderItem(orderId, new_orderItem);
        return new_orderItem;
    }

    public List<OrderItem> getOrderItems(long orderId) throws OrderNotFoundException {
        return orderService.getOrderItems(orderId);
    }

    public boolean deleteOrderItem(long orderId, long orderItemId) throws OrderItemNotFoundException, OrderNotFoundException {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElse(null);
        if (orderItem == null) {
            throw new OrderItemNotFoundException("Order item not found");
        }
        boolean isDeleted = orderService.deleteOrderItem(orderId, orderItem);
        orderItemRepository.deleteById(orderItemId);
        return isDeleted;
    }

    public OrderItem updateOrderItem(long orderId, long orderItemId, OrderItem updatedItem) throws OrderItemNotFoundException, OrderNotFoundException {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElse(null);
        if (orderItem == null) {
            throw new OrderItemNotFoundException("Order Item doesn`t exist");
        }
        if (updatedItem.getMenuItem() != null) {
            orderItem.setMenuItem(updatedItem.getMenuItem());
            orderItem.setAmount(orderItem.calculateAmount());
        }
        if (updatedItem.getQuantity() != 0) {
            orderItem.setQuantity(updatedItem.getQuantity());
            orderItem.setAmount(orderItem.calculateAmount());
        } else {
            orderService.deleteOrderItem(orderId, orderItem);
            orderItemRepository.deleteById(orderItemId);
        }
        return orderItem;
    }

}
