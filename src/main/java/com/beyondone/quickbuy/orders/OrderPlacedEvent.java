package com.beyondone.quickbuy.orders;

import com.beyondone.quickbuy.utils.Event;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class OrderPlacedEvent extends Event {
    private Order order;
}
