package com.beyondone.quickbuy.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MoneyTest {

    @Test
    public void testConstructorSuccess() {
        final Money money = new Money(BigDecimal.valueOf(1.0));
        assertEquals(money.getValue(), BigDecimal.valueOf(1.0));

        final Money money2 = new Money(BigDecimal.valueOf(0.0));
        assertEquals(money2.getValue(), BigDecimal.valueOf(0.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorFailure() {
        new Money(BigDecimal.valueOf(-1.0));
    }
}