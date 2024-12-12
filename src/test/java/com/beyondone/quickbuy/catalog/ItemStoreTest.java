package com.beyondone.quickbuy.catalog;

import com.beyondone.quickbuy.utils.Money;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ItemStoreTest {

    private ItemStore itemStore;

    private HashMap<String, Item> items;

    @Before
    public void setUp() {
        items = new HashMap<>();
        final Item item1 = ItemFixture.getItemOne();
        final Item item2 = ItemFixture.getItemTwo();
        items.put(item1.getId(), item1);
        items.put(item2.getId(), item2);
        itemStore = new ItemStore(items);
    }

    @Test
    public void getTest() throws ItemNotFoundException {
        final Item itemOne = ItemFixture.getItemOne();
        final Item item = itemStore.get(itemOne.getId());
        assertNotNull(item);
        assertEquals(itemOne.getId(), item.getId());
        assertEquals(itemOne.getName(), item.getName());
        assertEquals(itemOne.getDescription(), item.getDescription());
        assertEquals(itemOne.getPrice().getValue(), item.getPrice().getValue());
    }

    @Test(expected = ItemNotFoundException.class)
    public void getTestShouldThrowException() throws ItemNotFoundException {
        itemStore.get("fakeItemId");
    }

    @Test
    public void getAllItemsTest() {
        final List<Item> items = itemStore.getAllItems();
        assertEquals(2, items.size());
    }

    @Test
    public void addItemTest() {
        final Item item = Item.builder()
                .id("newItemId")
                .name("newName")
                .description("newDescription")
                .price(new Money(BigDecimal.valueOf(5.2)))
                .cost(new Money(BigDecimal.valueOf(2.3)))
                .build();
        itemStore.add(item);

        assertTrue(items.containsKey("newItemId"));
        final Item newItem = items.get("newItemId");
        assertEquals("newName", newItem.getName());
        assertEquals("newDescription", newItem.getDescription());
        assertEquals(new Money(BigDecimal.valueOf(5.2)).getValue(), newItem.getPrice().getValue());
        assertEquals(new Money(BigDecimal.valueOf(2.3)).getValue(), newItem.getCost().getValue());
    }

    @Test
    public void deleteTest() {
        final String itemId = ItemFixture.getItemOne().getId();
        itemStore.delete(itemId);
        assertFalse(items.containsKey(itemId));
    }
}