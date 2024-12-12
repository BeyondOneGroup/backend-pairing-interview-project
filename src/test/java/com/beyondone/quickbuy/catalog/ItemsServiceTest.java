package com.beyondone.quickbuy.catalog;

import com.beyondone.quickbuy.model.items.LineDto;
import com.beyondone.quickbuy.model.items.management.ItemIdsDto;
import com.beyondone.quickbuy.model.items.management.ItemsManagementItemDto;
import com.beyondone.quickbuy.model.items.management.ItemsManagementLineDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemsServiceTest {

    @InjectMocks
    private ItemsService itemsService;

    @Mock
    private ItemAvailabilityManager itemAvailabilityManager;
    @Mock
    private ItemStore itemStore;

    @Test
    public void getAllAvailableItemsTest() throws ItemNotFoundException {

        final HashMap<String, Integer> itemAvailabilityMap = new HashMap<>();
        itemAvailabilityMap.put("item1", 10);
        final Item item = ItemFixture.getItemOne();

        when(itemAvailabilityManager.getAvailableItemQuantities()).thenReturn(itemAvailabilityMap);
        when(itemStore.get("item1")).thenReturn(item);

        final List<LineDto> availableItems = itemsService.getAllAvailableItems();

        assertEquals(1, availableItems.size());
    }

    @Test
    public void getAllAvailableItemsTestShouldNotThrowErrorIfItemIsNotPresentButQuantityIsGreaterThanZero() throws ItemNotFoundException {

        final HashMap<String, Integer> itemAvailabilityMap = new HashMap<>();
        itemAvailabilityMap.put("item1", 10);

        when(itemAvailabilityManager.getAvailableItemQuantities()).thenReturn(itemAvailabilityMap);
        when(itemStore.get(anyString())).thenThrow(ItemNotFoundException.class);

        final List<LineDto> availableItems = itemsService.getAllAvailableItems();

        assertEquals(0, availableItems.size());
    }

    @Test
    public void getAllItemsTest() throws ItemNotFoundException {

        final HashMap<String, Integer> itemAvailabilityMap = new HashMap<>();
        itemAvailabilityMap.put("item1", 10);
        itemAvailabilityMap.put("item2", 0);
        final Item item1 = ItemFixture.getItemOne();

        final Item item2 = ItemFixture.getItemTwo();

        when(itemAvailabilityManager.getAllItemQuantities()).thenReturn(itemAvailabilityMap);
        when(itemStore.get("item1")).thenReturn(item1);
        when(itemStore.get("item2")).thenReturn(item2);

        final List<ItemsManagementLineDto> availableItems = itemsService.getAllItems();

        assertEquals(2, availableItems.size());
    }

    @Test
    public void getAllItemsTestShouldNotThrowErrorIfItemIsNotPresentButQuantityIsGreaterThanZero() throws ItemNotFoundException {

        final HashMap<String, Integer> itemAvailabilityMap = new HashMap<>();
        itemAvailabilityMap.put("item1", 10);
        itemAvailabilityMap.put("item2", 0);

        when(itemAvailabilityManager.getAllItemQuantities()).thenReturn(itemAvailabilityMap);
        when(itemStore.get(anyString())).thenThrow(ItemNotFoundException.class);

        final List<ItemsManagementLineDto> availableItems = itemsService.getAllItems();

        assertEquals(0, availableItems.size());
    }

    @Test
    public void addItemTest() {

        final ItemsManagementItemDto item = ItemFixture.getItemManagementItemDto();
        itemsService.addItem(item, 10);

        verify(itemStore, times(1)).add(any(Item.class));
        verify(itemAvailabilityManager, times(1)).setItemQuantity(anyString(), anyInt());
    }

    @Test
    public void deleteTest() {

        final ItemIdsDto itemIdsDto = new ItemIdsDto();
        itemIdsDto.add("itemIdToDelete");
        itemsService.delete(itemIdsDto);

        verify(itemStore, times(1)).delete(any(String.class));
        verify(itemAvailabilityManager, times(1)).delete(any(String.class));
    }

}