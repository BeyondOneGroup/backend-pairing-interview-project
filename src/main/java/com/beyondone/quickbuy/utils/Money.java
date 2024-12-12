package com.beyondone.quickbuy.utils;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Data
public class Money {

    @Getter
    private final BigDecimal value;

    public Money(BigDecimal value) {
        if (value == null || isLessThanZero(value)) {
            throw new IllegalArgumentException("value can not be null or less than 0");
        }
        this.value = value;
    }

    private boolean isLessThanZero(BigDecimal value) {
        return BigDecimal.ZERO.compareTo(value) > 0;
    }
}
