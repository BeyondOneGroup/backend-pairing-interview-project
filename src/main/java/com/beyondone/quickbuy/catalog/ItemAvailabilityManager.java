package com.beyondone.quickbuy.catalog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ItemAvailabilityManager {

    private static final int MIN_ITEM_QUANTITY_THRESHOLD = 5;
    private final Map<String, Integer> itemQuantity;

    @Autowired
    public ItemAvailabilityManager(Map<String, Integer> itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Map<String, Integer> getAvailableItemQuantities() {
        return itemQuantity.entrySet()
                .stream()
                .filter(item -> item.getValue() > 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, Integer> getAllItemQuantities() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemId, int quantity) {
        itemQuantity.put(itemId, quantity);
    }

    public void delete(String itemId) {
        itemQuantity.remove(itemId);
    }

    public void decrementQuantity(String itemId) throws ItemNotFoundException {
        if (!itemQuantity.containsKey(itemId)) {
            throw new ItemNotFoundException("item against id:{%s} not found in store", itemId);
        }
        final Integer quantity = itemQuantity.get(itemId);
        final int newQuantity = quantity - 1;
        itemQuantity.put(itemId, newQuantity);

        if (newQuantity < MIN_ITEM_QUANTITY_THRESHOLD) {
            log.info("Stock for itemId:{} is about to end", itemId);
        }
    }
}
