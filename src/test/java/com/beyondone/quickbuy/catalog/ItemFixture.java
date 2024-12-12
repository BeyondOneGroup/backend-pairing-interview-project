package com.beyondone.quickbuy.catalog;

import com.beyondone.quickbuy.model.items.management.ItemsManagementItemDto;
import com.beyondone.quickbuy.utils.Money;

import java.math.BigDecimal;

public class ItemFixture {

    public static Item getItemOne() {
        return Item.builder()
                .id("item1")
                .name("Bottle")
                .description("Water bottle")
                .price(new Money(BigDecimal.valueOf(10.4)))
                .cost(new Money(BigDecimal.valueOf(10.4)))
                .build();
    }

    public static Item getItemTwo() {
        return Item.builder()
                .id("item2")
                .name("Bubble")
                .description("Mint flavoured bubble")
                .price(new Money(BigDecimal.valueOf(2.5)))
                .cost(new Money(BigDecimal.valueOf(1.0)))
                .build();
    }

    public static Item getItemThree() {
        return Item.builder()
                .id("item3")
                .name("Chips")
                .description("Crunchy chips")
                .price(new Money(BigDecimal.valueOf(1.5)))
                .cost(new Money(BigDecimal.valueOf(1.0)))
                .build();
    }

    public static ItemsManagementItemDto getItemManagementItemDto() {
        final ItemsManagementItemDto itemsManagementItemDto = new ItemsManagementItemDto();
        itemsManagementItemDto.setId("itemId");
        itemsManagementItemDto.setName("itemName");
        itemsManagementItemDto.setDescription("itemDescription");
        itemsManagementItemDto.setPrice(BigDecimal.valueOf(1.3));
        itemsManagementItemDto.setCost(BigDecimal.valueOf(0.5));
        return itemsManagementItemDto;
    }
}
