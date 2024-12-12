package com.beyondone.quickbuy.catalog;

import com.beyondone.quickbuy.model.items.LineDto;
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
public class ItemsControllerTest {

    @InjectMocks
    ItemsController itemsController;

    @Mock
    private ItemsService itemsService;

    @Test
    public void getInventory() {

        when(itemsService.getAllAvailableItems()).thenReturn(Collections.singletonList(new LineDto()));
        final ResponseEntity<List<LineDto>> inventory = itemsController.getItems();
        assertEquals(HttpStatus.OK, inventory.getStatusCode());
        verify(itemsService, times(1)).getAllAvailableItems();
    }

}