package com.beyondone.quickbuy.catalog;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String msg, Object... args) {
        log.error(String.format(msg, args));
    }
}
