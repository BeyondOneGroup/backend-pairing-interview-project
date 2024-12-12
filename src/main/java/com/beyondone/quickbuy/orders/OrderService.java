package com.beyondone.quickbuy.orders;

import com.beyondone.quickbuy.model.orders.OrderDto;
import com.beyondone.quickbuy.utils.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    public static final String ORDER_PLACED_EVENT_NAME = "ORDER_PLACED";

    @Autowired
    EventPublisher eventPublisher;
    @Autowired
    OrderStore orderStore;

    public void placeOrder(OrderDto orderDto) {

        final Order order = Order.from(orderDto);

        orderStore.saveOrder(order);

        final OrderPlacedEvent event = OrderPlacedEvent.builder()
                .name(ORDER_PLACED_EVENT_NAME)
                .order(order)
                .build();
        eventPublisher.publishEvent(event);
    }

    public OrderDto getOrder(String orderId) {
        try {
            final Order order = orderStore.getOrder(orderId);
            return order.toOrderDto();
        } catch (OrderNotFoundException e) {
            return null;
        }
    }
}
