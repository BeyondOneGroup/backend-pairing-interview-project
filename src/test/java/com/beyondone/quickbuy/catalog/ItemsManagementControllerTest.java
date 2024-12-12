package com.beyondone.quickbuy.catalog;

import com.beyondone.quickbuy.model.items.management.ItemIdsDto;
import com.beyondone.quickbuy.model.items.management.ItemsManagementItemDto;
import com.beyondone.quickbuy.model.items.management.ItemsManagementLineDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ItemsManagementControllerTest {

    @InjectMocks
    ItemsManagementController itemsManagementController;
    @Mock
    private ItemsService itemsService;

    @Test
    public void getInventoryTest() {

        when(itemsService.getAllItems()).thenReturn(Collections.singletonList(new ItemsManagementLineDto()));
        final ResponseEntity<List<ItemsManagementLineDto>> inventory = itemsManagementController.getInventory();
        assertEquals(HttpStatus.OK, inventory.getStatusCode());
        verify(itemsService, times(1)).getAllItems();
    }

    @Test
    public void upsertTest() {
        final ItemsManagementItemDto itemManagementItemDto = ItemFixture.getItemManagementItemDto();
        final int quantity = 1;
        final ItemsManagementLineDto itemsManagementLineDto = new ItemsManagementLineDto();
        itemsManagementLineDto.setItem(itemManagementItemDto);
        itemsManagementLineDto.setQuantity(quantity);
        final List<ItemsManagementLineDto> itemsManagementLineDtos = Collections.singletonList(itemsManagementLineDto);

        final ResponseEntity<List<ItemsManagementLineDto>> inventory = itemsManagementController.upsert(itemsManagementLineDtos);

        assertEquals(HttpStatus.OK, inventory.getStatusCode());
        final ItemsManagementLineDto response = inventory.getBody().get(0);
        assertEquals(Integer.valueOf(quantity), response.getQuantity());
        assertEquals(itemManagementItemDto, response.getItem());
        verify(itemsService, times(1)).addItem(itemManagementItemDto, quantity);
    }

    @Test
    public void deleteTest() {
        final ItemIdsDto itemIdsDto = new ItemIdsDto();
        final ResponseEntity<ItemIdsDto> response = itemsManagementController.delete(itemIdsDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(itemIdsDto, response.getBody());
    }

}