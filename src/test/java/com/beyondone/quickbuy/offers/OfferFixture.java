package com.beyondone.quickbuy.offers;

import com.beyondone.quickbuy.utils.Money;

import java.math.BigDecimal;

public class OfferFixture {

    public static Offer getOfferOne() {
        return Offer.builder()
                .id("offerOne")
                .description("offerOneDescription")
                .name("offerName")
                .itemId("offerOneItemId")
                .priceReduction(new Money(BigDecimal.valueOf(1)))
                .quantityThreshold(5)
                .build();
    }

    public static Offer getOfferTwo() {
        return Offer.builder()
                .id("offerTwo")
                .description("offerTwoDescription")
                .name("offerTwoName")
                .itemId("offerTwoItemId")
                .priceReduction(new Money(BigDecimal.valueOf(2)))
                .quantityThreshold(2)
                .build();
    }
}
