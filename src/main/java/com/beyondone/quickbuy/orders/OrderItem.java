package com.beyondone.quickbuy.orders;

import com.beyondone.quickbuy.model.orders.OrderItemDto;
import com.beyondone.quickbuy.utils.Money;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItem {
    private String id;
    private String name;
    private Money originalPrice;
    private Money discountedPrice;

    public static OrderItem from(OrderItemDto item) {
        return OrderItem.builder()
                .id(item.getId())
                .name(item.getName())
                .originalPrice(new Money(item.getOriginalPrice()))
                .discountedPrice(new Money(item.getDiscountedPrice()))
                .build();
    }

    public OrderItemDto toOrderItemDto() {
        final OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(this.getId());
        orderItemDto.setName(this.getName());
        orderItemDto.setDiscountedPrice(this.getDiscountedPrice().getValue());
        orderItemDto.setOriginalPrice(this.getOriginalPrice().getValue());
        return orderItemDto;
    }
}
