package com.beyondone.quickbuy.orders;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String msg, Object... args) {
        log.error(String.format(msg, args));
    }
}
