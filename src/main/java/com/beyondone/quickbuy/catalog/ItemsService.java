package com.beyondone.quickbuy.catalog;

import com.beyondone.quickbuy.model.items.ItemDto;
import com.beyondone.quickbuy.model.items.LineDto;
import com.beyondone.quickbuy.model.items.management.ItemIdsDto;
import com.beyondone.quickbuy.model.items.management.ItemsManagementItemDto;
import com.beyondone.quickbuy.model.items.management.ItemsManagementLineDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ItemsService {

    final ItemStore itemStore;
    final ItemAvailabilityManager itemAvailabilityManager;

    @Autowired
    public ItemsService(ItemStore itemStore, ItemAvailabilityManager itemAvailabilityManager) {
        this.itemStore = itemStore;
        this.itemAvailabilityManager = itemAvailabilityManager;
    }

    public List<LineDto> getAllAvailableItems() {
        final Map<String, Integer> availableItemQuantities = itemAvailabilityManager.getAvailableItemQuantities();

        return availableItemQuantities.entrySet()
                .stream()
                .map(this::getLineDto)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<ItemsManagementLineDto> getAllItems() {
        final Map<String, Integer> itemQuantities = itemAvailabilityManager.getAllItemQuantities();

        return itemQuantities.entrySet()
                .stream()
                .map(this::getItemsManagementLineDto)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private LineDto getLineDto(Map.Entry<String, Integer> entry) {
        final String itemId = entry.getKey();
        final Integer quantity = entry.getValue();

        final Item item = getItem(itemId);

        if (item == null) {
            log.info("Found a case where item quantity is greater than 0 but item is not present in store");
            return null;
        }

        final ItemDto itemDto = item.toItemDto();
        final LineDto lineDto = new LineDto();
        lineDto.setQuantity(quantity);
        lineDto.setItem(itemDto);
        return lineDto;
    }


    private Item getItem(String itemId) {
        try {
            return itemStore.get(itemId);
        } catch (ItemNotFoundException e) {
            return null;
        }
    }

    private ItemsManagementLineDto getItemsManagementLineDto(Map.Entry<String, Integer> entry) {
        final String itemId = entry.getKey();
        final Integer quantity = entry.getValue();

        final Item item = getItem(itemId);
        if (item == null) {
            log.info("Found a case where item quantity is greater than 0 but item is not present in store");
            return null;
        }

        final ItemsManagementItemDto itemsManagementItemDto = item.toItemsManagementItemDto();
        final ItemsManagementLineDto itemsManagementLineDto = new ItemsManagementLineDto();
        itemsManagementLineDto.setQuantity(quantity);
        itemsManagementLineDto.setItem(itemsManagementItemDto);
        return itemsManagementLineDto;
    }


    public void addItem(ItemsManagementItemDto itemsManagementItemDto, int quantity) {
        final Item item = Item.from(itemsManagementItemDto);
        itemStore.add(item);
        itemAvailabilityManager.setItemQuantity(item.getId(), quantity);
    }

    public void delete(ItemIdsDto itemIdsDto) {
        itemIdsDto.forEach(itemId -> {
            itemStore.delete(itemId);
            itemAvailabilityManager.delete(itemId);
        });
    }
}