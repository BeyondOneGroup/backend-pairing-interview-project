package com.beyondone.quickbuy.orders;

import com.beyondone.quickbuy.utils.Money;

import java.math.BigDecimal;
import java.util.Collections;

public class OrderFixture {

    public static Order getOrderOne() {
        final OrderItem orderItem = OrderItem.builder()
                .id("OrderItemOne")
                .name("ItemOne")
                .originalPrice(new Money(BigDecimal.valueOf(15)))
                .discountedPrice(new Money(BigDecimal.valueOf(10)))
                .build();
        OrderOffer orderOffer = OrderOffer.builder()
                .id("OrderOfferOne")
                .name("OfferOne")
                .itemId("Juice")
                .priceReduction(new Money(new BigDecimal(5)))
                .quantityThreshold(2)
                .build();
        return Order.builder()
                .id("OrderOne")
                .items(Collections.singletonList(orderItem))
                .offers(Collections.singletonList(orderOffer))
                .build();
    }
}
