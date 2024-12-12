package com.beyondone.quickbuy;


import com.beyondone.quickbuy.catalog.ItemFixture;
import com.beyondone.quickbuy.catalog.ItemsManagementController;
import com.beyondone.quickbuy.model.items.management.ItemsManagementItemDto;
import com.beyondone.quickbuy.model.items.management.ItemsManagementLineDto;
import com.beyondone.quickbuy.model.orders.OrderDto;
import com.beyondone.quickbuy.orders.OrderController;
import com.beyondone.quickbuy.orders.OrderFixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SlowTest {

    @Autowired
    private OrderController orderController;
    @Autowired
    private ItemsManagementController itemsManagementController;

    @Test
    public void itemQuantityShouldBeDecrementedIfOrderIsPlaced() {
        // Add an item to catalog
        final ItemsManagementItemDto itemManagementItemDto = ItemFixture.getItemManagementItemDto();
        final ItemsManagementLineDto itemsManagementLineDto = new ItemsManagementLineDto();
        itemsManagementLineDto.setItem(itemManagementItemDto);
        itemsManagementLineDto.setQuantity(2);
        final List<ItemsManagementLineDto> itemsManagementLineDtos = Collections.singletonList(itemsManagementLineDto);
        itemsManagementController.upsert(itemsManagementLineDtos);

        // Place an order
        final OrderDto orderDto = OrderFixture.getOrderOne().toOrderDto();
        orderDto.getItems().forEach(orderItemDto -> orderItemDto.setId(itemManagementItemDto.getId()));
        final List<OrderDto> orderDtos = Collections.singletonList(orderDto);
        orderController.placeOrder(orderDtos);

        // Assert that item quantity is reduced
        final ResponseEntity<List<ItemsManagementLineDto>> inventory = itemsManagementController.getInventory();
        final List<ItemsManagementLineDto> updatedInventory = inventory.getBody();
        updatedInventory.forEach(lineDto -> {
            final ItemsManagementItemDto item = lineDto.getItem();
            if (Objects.equals(item.getId(), itemManagementItemDto.getId())) {
                int updatedQuantity = lineDto.getQuantity();
                assertEquals(1, updatedQuantity);
            }
        });
    }
}
