package com.beyondone.quickbuy.utils;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Event {
    private String name;
    private Object data;
}
