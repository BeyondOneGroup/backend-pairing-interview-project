package com.beyondone.quickbuy.orders;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class OrderStoreTest {

    private OrderStore orderStore;

    private Map<String, Order> orders;

    @Before
    public void setUp() {
        final Order order = OrderFixture.getOrderOne();
        orders = new HashMap<>();
        orders.put(order.getId(), order);
        orderStore = new OrderStore(orders);
    }

    @Test
    public void getOrderTest() throws OrderNotFoundException {
        final Order orderOne = OrderFixture.getOrderOne();

        final Order order = orderStore.getOrder(orderOne.getId());

        assertNotNull(order);
        assertEquals(orderOne.getId(), order.getId());

        for (int i = 0; i < order.getItems().size(); i++) {
            areEqual(orderOne.getItems().get(i), order.getItems().get(i));
        }
        for (int i = 0; i < order.getOffers().size(); i++) {
            areEqual(orderOne.getOffers().get(i), order.getOffers().get(i));
        }
    }

    @Test(expected = OrderNotFoundException.class)
    public void getOrderTestShouldThrowException() throws OrderNotFoundException {
        orderStore.getOrder("fakeOrderId");
    }

    @Test
    public void placeOrderTest() {
        final Order order = OrderFixture.getOrderOne();

        orderStore.saveOrder(order);

        assertTrue(orders.containsKey(order.getId()));

        final Order placedOrder = orders.get(order.getId());

        for (int i = 0; i < order.getItems().size(); i++) {
            areEqual(placedOrder.getItems().get(i), order.getItems().get(i));
        }
        for (int i = 0; i < order.getOffers().size(); i++) {
            areEqual(placedOrder.getOffers().get(i), order.getOffers().get(i));
        }
    }

    private void areEqual(OrderItem expectedItem, OrderItem actualItem) {
        assertEquals(expectedItem.getId(), actualItem.getId());
        assertEquals(expectedItem.getName(), actualItem.getName());
        assertEquals(expectedItem.getDiscountedPrice().getValue(), actualItem.getDiscountedPrice().getValue());
        assertEquals(expectedItem.getOriginalPrice().getValue(), actualItem.getOriginalPrice().getValue());
    }

    private void areEqual(OrderOffer expectedOffer, OrderOffer actualOffer) {
        assertEquals(expectedOffer.getId(), actualOffer.getId());
        assertEquals(expectedOffer.getName(), actualOffer.getName());
        assertEquals(expectedOffer.getItemId(), actualOffer.getItemId());
        assertEquals(expectedOffer.getPriceReduction().getValue(), actualOffer.getPriceReduction().getValue());
        assertEquals(expectedOffer.getQuantityThreshold(), actualOffer.getQuantityThreshold());
    }
}