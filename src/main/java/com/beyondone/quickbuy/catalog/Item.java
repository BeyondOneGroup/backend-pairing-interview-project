package com.beyondone.quickbuy.catalog;

import com.beyondone.quickbuy.model.items.ItemDto;
import com.beyondone.quickbuy.model.items.management.ItemsManagementItemDto;
import com.beyondone.quickbuy.utils.Money;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
    private String id;
    private String name;
    private String description;
    private Money price;
    private Money cost;

    public static Item from(ItemsManagementItemDto itemsManagementItemDto) {
        return Item.builder()
                .id(itemsManagementItemDto.getId())
                .name(itemsManagementItemDto.getName())
                .description(itemsManagementItemDto.getDescription())
                .price(new Money(itemsManagementItemDto.getPrice()))
                .cost(new Money(itemsManagementItemDto.getCost()))
                .build();
    }

    public ItemDto toItemDto() {
        final ItemDto itemDto = new ItemDto();
        itemDto.setId(this.getId());
        itemDto.setDescription(this.getDescription());
        itemDto.setName(this.getName());
        itemDto.setPrice(this.getPrice().getValue());
        return itemDto;
    }

    public ItemsManagementItemDto toItemsManagementItemDto() {
        final ItemsManagementItemDto itemsManagementItemDto = new ItemsManagementItemDto();
        itemsManagementItemDto.setId(this.getId());
        itemsManagementItemDto.setDescription(this.getDescription());
        itemsManagementItemDto.setName(this.getName());
        itemsManagementItemDto.setPrice(this.getPrice().getValue());
        itemsManagementItemDto.setCost(this.getCost().getValue());
        return itemsManagementItemDto;
    }
}
