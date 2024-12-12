package com.beyondone.quickbuy.orders;

import com.beyondone.quickbuy.model.orders.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrder(@RequestParam(value = "orderIds[]") List<String> orderIds) {
        final List<OrderDto> orderDtos = orderIds.stream()
                .map(orderService::getOrder)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDtos);
    }

    @PostMapping
    public ResponseEntity<List<OrderDto>> placeOrder(@RequestBody List<OrderDto> orderDtos) {
        orderDtos.forEach(orderService::placeOrder);
        return ResponseEntity.ok(orderDtos);
    }
}
