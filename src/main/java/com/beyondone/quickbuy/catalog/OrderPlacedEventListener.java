package com.beyondone.quickbuy.catalog;

import com.beyondone.quickbuy.orders.Order;
import com.beyondone.quickbuy.orders.OrderItem;
import com.beyondone.quickbuy.orders.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class OrderPlacedEventListener {

    @Autowired
    ItemAvailabilityManager itemAvailabilityManager;

    @EventListener
    public void consume(OrderPlacedEvent event) {
        log.info("Received event:{}", event);

        final Order order = event.getOrder();
        final List<OrderItem> items = order.getItems();

        items.forEach(item -> {
            try {
                itemAvailabilityManager.decrementQuantity(item.getId());
            } catch (ItemNotFoundException e) {
                log.info("Found a case where an order is placed with an item which is not present in store");
            }
        });
    }
}