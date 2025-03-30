package org.example.service;

import org.example.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final List<Order> orders = new ArrayList<>();

    public List<Order> getAllOrders() {
        return orders;
    }

    public Order getOrderById(Long id) {
        return orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Order createOrder(Order order) {
        order.setId((long) (orders.size() + 1)); // Simulate auto-increment ID
        orders.add(order);
        return order;
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        Order order = getOrderById(id);
        if (order != null) {
            order.setClientId(updatedOrder.getClientId());
            order.setOrderDetails(updatedOrder.getOrderDetails());
            order.setTotalAmount(updatedOrder.getTotalAmount());
        }
        return order;
    }

    public void deleteOrder(Long id) {
        orders.removeIf(order -> order.getId().equals(id));
    }
}