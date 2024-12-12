package com.beyondone.quickbuy.orders;

import com.beyondone.quickbuy.model.orders.OrderOfferDto;
import com.beyondone.quickbuy.utils.Money;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderOffer {
    private String id;
    private String name;
    private String itemId;
    private Money priceReduction;
    private Integer quantityThreshold;

    public static OrderOffer from(OrderOfferDto offer) {
        return OrderOffer.builder()
                .id(offer.getId())
                .name(offer.getName())
                .itemId(offer.getItemId())
                .quantityThreshold(offer.getQuantityThreshold())
                .priceReduction(new Money(offer.getPriceReduction()))
                .build();
    }

    public OrderOfferDto toOrderOfferDto() {
        final OrderOfferDto orderOfferDto = new OrderOfferDto();
        orderOfferDto.setId(this.getId());
        orderOfferDto.setName(this.getName());
        orderOfferDto.setItemId(this.getItemId());
        orderOfferDto.setPriceReduction(this.getPriceReduction().getValue());
        orderOfferDto.setQuantityThreshold(this.getQuantityThreshold());
        return orderOfferDto;
    }
}
