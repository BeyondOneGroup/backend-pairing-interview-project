package com.beyondone.quickbuy.orders;

import com.beyondone.quickbuy.model.orders.OrderDto;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    @InjectMocks
    OrderController orderController;

    @Mock
    OrderService orderService;

    @Test
    public void getOrdersTest() {
        final List<String> ids = Collections.singletonList("orderId1");

        final ResponseEntity<List<OrderDto>> response = orderController.getOrder(ids);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(orderService, times(1)).getOrder(anyString());
    }

    @Test
    public void getOrdersTestShouldNotFailOnInvalidOrderIds() {
        final List<String> ids = Collections.singletonList("orderId1");
        when(orderService.getOrder(anyString())).thenReturn(null);

        final ResponseEntity<List<OrderDto>> response = orderController.getOrder(ids);
        assertEquals(0, response.getBody().size());

        verify(orderService, times(1)).getOrder(anyString());
    }

    @Test
    public void placeOrdersTest() {
        final List<OrderDto> orderDtos = Collections.singletonList(new OrderDto());

        final ResponseEntity<List<OrderDto>> response = orderController.placeOrder(orderDtos);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(orderService, times(1)).placeOrder(any((OrderDto.class)));
    }
}
