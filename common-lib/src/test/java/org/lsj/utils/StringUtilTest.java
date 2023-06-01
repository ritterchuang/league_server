package org.lsj.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringUtilTest {

    @Test
    void GivenCommon_WhenGetEmptyString_ThenReturnEmptyString() {
        final String expected = "";

        final String actual = StringUtil.getInstance().getEmptyString();

        assertEquals(expected, actual);
    }

    @Test
    void GivenCommon_WhenCommaString_ThenReturnComma() {
        final String expected = ",";

        final String actual = StringUtil.getInstance().getCommaString();

        assertEquals(expected, actual);
    }

    @Test
    void GivenNull_WhenIsNull_ThenTrue() {
        final boolean expected = true;

        final boolean actual = StringUtil.getInstance().isNull(null);

        assertEquals(expected, actual);
    }

    @Test
    void GivenEmptyString_WhenIsNull_ThenFalse() {
        final String str = "";
        final boolean expected = false;

        final boolean actual = StringUtil.getInstance().isNull(str);

        assertEquals(expected, actual);
    }

}
