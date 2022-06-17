package com.mysticaldream.common.other;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @description: BigDecimalTest
 * @date: 2022/6/17 11:10
 * @author: MysticalDream
 */
public class BigDecimalTest {
    @Test
    public void test() {
        BigDecimal bigDecimal1 = new BigDecimal("1.0");
        BigDecimal bigDecimal2 = new BigDecimal("4.0");
        BigDecimal divide = bigDecimal1.divide(bigDecimal2, RoundingMode.FLOOR);
        System.out.println(divide);
    }
}
