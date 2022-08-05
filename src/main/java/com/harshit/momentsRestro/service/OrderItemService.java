package com.harshit.momentsRestro.service;

import com.harshit.momentsRestro.entity.OrderItem;
import com.harshit.momentsRestro.exception.OrderItemNotFoundException;
import com.harshit.momentsRestro.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public List<OrderItem> getOrderItem(long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    public void deleteOrderItem(long orderId) {
        orderItemRepository.deleteById(orderId);
    }

    public OrderItem updateOrderItem(long id, OrderItem updatedItem) throws OrderItemNotFoundException {
        OrderItem item = orderItemRepository.findById(id).orElse(null);
        if(item == null){
            throw new OrderItemNotFoundException("Order Item doesn`t exist");
        }
        if(updatedItem.getMenuItem() != null){
            item.setMenuItem(updatedItem.getMenuItem());
            item.setAmount(item.calculateAmount());
        }
        if(updatedItem.getQuantity() != 0){
            item.setQuantity(updatedItem.getQuantity());
            item.setAmount(item.calculateAmount());
        }else{
            orderItemRepository.deleteById(id);
        }
        return item;
    }

}
