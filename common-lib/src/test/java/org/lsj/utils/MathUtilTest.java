package org.lsj.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MathUtilTest {

    @Test
    void GivenTwoDouble_WhenAdd_ThenReturnResult() {
        final double value1 = 100.444D;
        final double value2 = 200.555D;
        final double expected = 300.999D;

        final double actual = MathUtil.add(value1, value2);

        assertEquals(expected, actual);
    }

    @Test
    void GivenTwoDouble_WhenSubtract_ThenReturnResult() {
        final double value1 = 200.999D;
        final double value2 = 100.222D;
        final double expected = 100.777D;

        final double actual = MathUtil.subtract(value1, value2);

        assertEquals(expected, actual);
    }

    @Test
    void GivenTwoDouble_WhenMultiply_ThenReturnResult() {
        final double value1 = 200.111D;
        final double value2 = 6D;
        final double expected = 1200.666D;

        final double actual = MathUtil.multiply(value1, value2);

        assertEquals(expected, actual);
    }

    @Test
    void GivenTwoDouble_WhenDivide_ThenReturnResult() {
        final double value1 = 101D;
        final double value2 = 3D;
        final double expected = 33.6666666666D;

        final double actual = MathUtil.divide(value1, value2);

        assertEquals(expected, actual);
    }

    @Test
    void GivenDouble_WhenMoneyScale_ThenReturnDoubleWithUsingFloorForTwoOfTheScale() {
        final double value = 100.125D;
        final double expected = 100.12D;

        final double actual = MathUtil.moneyScale(value);

        assertEquals(expected, actual);
    }

}
