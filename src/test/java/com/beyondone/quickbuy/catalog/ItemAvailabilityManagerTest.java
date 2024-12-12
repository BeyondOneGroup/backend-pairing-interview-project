package com.beyondone.quickbuy.catalog;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ItemAvailabilityManagerTest {

    private ItemAvailabilityManager itemAvailabilityManager;

    private HashMap<String, Integer> itemAvailability;

    @Before
    public void setUp() {
        itemAvailability = new HashMap<>();
        itemAvailability.put(ItemFixture.getItemOne().getId(), 10);
        itemAvailability.put(ItemFixture.getItemTwo().getId(), 0);
        this.itemAvailabilityManager = new ItemAvailabilityManager(itemAvailability);
    }

    @Test
    public void getAvailableItemsTest() {
        final Map<String, Integer> availableItems = itemAvailabilityManager.getAvailableItemQuantities();
        assertNotNull(availableItems);
    }

    @Test
    public void getAvailableItemsShouldReturnAvailableItemsOnlyTest() {
        final Map<String, Integer> availableItems = itemAvailabilityManager.getAvailableItemQuantities();
        assertNotNull(availableItems);
        assertEquals(1, availableItems.size());
    }

    @Test
    public void getAllItemQuantitiesTest() {
        final Map<String, Integer> items = itemAvailabilityManager.getAllItemQuantities();
        assertEquals(2, items.size());

    }

    @Test
    public void setItemQuantityTest() {
        itemAvailabilityManager.setItemQuantity("newItem", 10);
        assertTrue(itemAvailability.containsKey("newItem"));
        assertEquals(10, itemAvailability.get("newItem").intValue());
    }

    @Test
    public void deleteTest() {
        final String itemId = ItemFixture.getItemOne().getId();
        itemAvailabilityManager.delete(itemId);
        assertFalse(itemAvailability.containsKey(itemId));
    }

    @Test
    public void decrementQuantityTest() throws ItemNotFoundException {
        final Item itemOne = ItemFixture.getItemOne();
        itemAvailabilityManager.decrementQuantity(itemOne.getId());

        assertEquals(Integer.valueOf(9), itemAvailability.get(itemOne.getId()));
    }

    @Test(expected = ItemNotFoundException.class)
    public void decrementQuantityForFakeItemShouldThrowException() throws ItemNotFoundException {
        itemAvailabilityManager.decrementQuantity("fakeItemId");
    }
}