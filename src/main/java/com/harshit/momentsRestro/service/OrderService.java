package com.harshit.momentsRestro.service;

import com.harshit.momentsRestro.entity.DeliveryBoy;
import com.harshit.momentsRestro.entity.Order;
import com.harshit.momentsRestro.entity.Payment;
import com.harshit.momentsRestro.exception.InvalidPaymentException;
import com.harshit.momentsRestro.exception.OrderAlreadyPaidException;
import com.harshit.momentsRestro.exception.OrderNotFoundException;
import com.harshit.momentsRestro.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/order")
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Order createOrder(Order order) {
        order.setStatus(Order.Status.PLACED.name());
        return orderRepository.save(order);
    }

    public Order getOrder(long id) throws OrderNotFoundException {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) throw new OrderNotFoundException("Order not found");
        return order;
    }

    public Order cancelOrder(long orderId) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) throw new OrderNotFoundException("Order not found");
        order.setStatus(Order.Status.CANCELLED.name());
        orderRepository.save(order);
        return order;
    }

    public void deleteOrder(long orderId) {
        orderRepository.deleteById(orderId);
    }

    public Order updateOrder(long orderId, Order updatedOrder) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) throw new OrderNotFoundException("Order not found");
        if (updatedOrder.getOrderItemList() != null) {
            order.setOrderItemList(updatedOrder.getOrderItemList());
            order.calculateAmount();
        }
        if (updatedOrder.getDeliveryBoy() != null) {
            order.setDeliveryBoy(updatedOrder.getDeliveryBoy());
        }
        return orderRepository.save(order);
    }

    public Order payOrder(long orderId, Payment payment) throws OrderNotFoundException, InvalidPaymentException {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) throw new OrderNotFoundException("Order not found");
        double paidAmount = payment.getAmount();
        double orderAmount = order.getTotalAmount();
        if (paidAmount != orderAmount) {
            throw new InvalidPaymentException("Paid amount mismatches order amount");
        }
        order.setPayment(payment);
        return orderRepository.save(order);
    }

    public Order deliverOrder(long orderId, DeliveryBoy deliveryBoy) throws OrderNotFoundException, OrderAlreadyPaidException {
        Order order = getOrder(orderId);
        if(order.getDeliveryBoy() != null){
            throw new OrderAlreadyPaidException("Order already paid");
        }
        order.setDeliveryBoy(deliveryBoy);
        order.setStatus(Order.Status.COMPLETED.name());
        return orderRepository.save(order);
    }
}
