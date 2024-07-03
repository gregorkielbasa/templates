package com.example.jdbctemplate;

import java.util.List;
import java.util.Objects;

public class Order {
    private static final long ID_MIN = 1000;
    private static final long ID_MAX = 9999;

    private final long orderId;
    private final long customerId;
    private final List<OrderItem> items;

    public Order(long orderId, long customerId) {
        validateId(orderId);
        this.orderId = orderId; // The ID will be generated by the database
        this.customerId = customerId;
        this.items = List.of();
    }

    public Order withItems(List<OrderItem> items) {
        return new Order(this.orderId, this.customerId, items);
    }

    public Order(long customerId, List<OrderItem> items) {
        this.orderId = 0; // The ID will be generated by the database
        this.customerId = customerId;
        validateItems(items);
        this.items = List.copyOf(items);
    }

    private Order(long orderId, long customerId, List<OrderItem> items) {
        validateId(orderId);
        this.orderId = orderId;
        this.customerId = customerId;
        validateItems(items);
        this.items = List.copyOf(items);
    }

    private void validateId(long id) {
        if (id < ID_MIN || id > ID_MAX)
            throw new RuntimeException();
    }

    private void validateItems(List<OrderItem> items) {
        if (items == null || items.isEmpty())
            throw new RuntimeException();
    }

    public long getOrderId() {
        return orderId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public List<OrderItem> getItems() {
        return List.copyOf(items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerId, items);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId && customerId == order.customerId && Objects.equals(items, order.items);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", items=" + items +
                '}';
    }
}

