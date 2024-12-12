package com.beyondone.quickbuy.orders;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderStore {

    private final Map<String, Order> store;

    public OrderStore(Map<String, Order> store) {
        this.store = store;
    }

    public Order getOrder(String orderId) throws OrderNotFoundException {
        if (!store.containsKey(orderId)) {
            throw new OrderNotFoundException("Unable to find order against id:{%s}", orderId);
        }
        return store.get(orderId);
    }

    public void saveOrder(Order order) {
        store.put(order.getId(), order);
    }

}
