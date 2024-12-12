package com.beyondone.quickbuy.orders;

import com.beyondone.quickbuy.model.orders.OrderDto;
import com.beyondone.quickbuy.model.orders.OrderItemDto;
import com.beyondone.quickbuy.model.orders.OrderOfferDto;
import com.beyondone.quickbuy.utils.EventPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    OrderService orderService;
    @Mock
    OrderStore orderStore;
    @Mock
    EventPublisher eventPublisher;

    @Test
    public void placeOrder() {

        final OrderDto orderDto = OrderFixture.getOrderOne().toOrderDto();
        orderService.placeOrder(orderDto);

        verify(eventPublisher, times(1)).publishEvent(any(OrderPlacedEvent.class));
        verify(orderStore, times(1)).saveOrder(any());
    }

    @Test
    public void getOrder() throws OrderNotFoundException {

        final Order orderOne = OrderFixture.getOrderOne();
        when(orderStore.getOrder(orderOne.getId())).thenReturn(orderOne);

        final OrderDto order = orderService.getOrder(orderOne.getId());

        verify(orderStore, times(1)).getOrder(orderOne.getId());
        for (int i = 0; i < order.getItems().size(); i++) {
            areEqual(orderOne.getItems().get(i), order.getItems().get(i));
        }
        for (int i = 0; i < order.getOffers().size(); i++) {
            areEqual(orderOne.getOffers().get(i), order.getOffers().get(i));
        }
    }

    @Test
    public void getOrderShouldReturnNull() throws OrderNotFoundException {

        final Order orderOne = OrderFixture.getOrderOne();
        when(orderStore.getOrder(anyString())).thenThrow(OrderNotFoundException.class);

        final OrderDto orderDto = orderService.getOrder(orderOne.getId());

        assertNull(orderDto);
        verify(orderStore, times(1)).getOrder(orderOne.getId());
    }

    private void areEqual(OrderItem expectedItem, OrderItemDto actualItem) {
        assertEquals(expectedItem.getId(), actualItem.getId());
        assertEquals(expectedItem.getName(), actualItem.getName());
        assertEquals(expectedItem.getDiscountedPrice().getValue(), actualItem.getDiscountedPrice());
        assertEquals(expectedItem.getOriginalPrice().getValue(), actualItem.getOriginalPrice());
    }

    private void areEqual(OrderOffer expectedOffer, OrderOfferDto actualOffer) {
        assertEquals(expectedOffer.getId(), actualOffer.getId());
        assertEquals(expectedOffer.getName(), actualOffer.getName());
        assertEquals(expectedOffer.getItemId(), actualOffer.getItemId());
        assertEquals(expectedOffer.getPriceReduction().getValue(), actualOffer.getPriceReduction());
        assertEquals(expectedOffer.getQuantityThreshold(), actualOffer.getQuantityThreshold());
    }
}