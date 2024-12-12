package com.beyondone.quickbuy.orders;

import com.beyondone.quickbuy.model.orders.OrderDto;
import com.beyondone.quickbuy.model.orders.OrderItemDto;
import com.beyondone.quickbuy.model.orders.OrderOfferDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class Order {
    private String id;
    private List<OrderItem> items;
    private List<OrderOffer> offers;

    public static Order from(OrderDto orderDto) {
        final List<OrderItemDto> items = orderDto.getItems();
        final List<OrderItem> orderItems = items.stream()
                .map(OrderItem::from)
                .collect(Collectors.toList());

        final List<OrderOfferDto> offers = orderDto.getOffers();
        final List<OrderOffer> orderOffers = offers.stream()
                .map(OrderOffer::from)
                .collect(Collectors.toList());

        return Order.builder()
                .id(orderDto.getId())
                .items(orderItems)
                .offers(orderOffers)
                .build();
    }

    public OrderDto toOrderDto() {
        final OrderDto orderDto = new OrderDto();

        final List<OrderItemDto> orderItemDtos = this.getItems().stream()
                .map(OrderItem::toOrderItemDto)
                .collect(Collectors.toList());

        final List<OrderOfferDto> orderOfferDtos = this.getOffers().stream()
                .map(OrderOffer::toOrderOfferDto)
                .collect(Collectors.toList());

        orderDto.setId(this.getId());
        orderDto.setItems(orderItemDtos);
        orderDto.setOffers(orderOfferDtos);

        return orderDto;
    }
}
