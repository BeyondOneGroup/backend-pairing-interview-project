package com.beyondone.quickbuy.catalog;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ItemStore {

    private final Map<String, Item> items;

    public ItemStore(Map<String, Item> items) {
        this.items = items;
    }

    public Item get(String id) throws ItemNotFoundException {
        if (!items.containsKey(id)) {
            throw new ItemNotFoundException("item against id:{%s} not found in store", id);
        }
        return items.get(id);
    }

    public List<Item> getAllItems() {
        return new ArrayList<>(items.values());
    }

    public void add(Item item) {
        items.put(item.getId(), item);
    }

    public void delete(String itemId) {
        items.remove(itemId);
    }
}
