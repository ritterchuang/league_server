package org.lsj.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonUtilTest {

    @Test
    public void GivenInputNull_WhenWriteValueAsStringWithoutException_thenOutputNull() {
        final String actual = JsonUtil.getInstance().writeValueAsStringWithoutException(null);

        assertEquals("null", actual);
    }

    @Test
    public void GivenDoubleAndUseDoubleNoScientificNotationJsonSerializer_WhenWriteValueAsString_ThenReturnNoScientificNotationText() throws JsonProcessingException {
        String expected = "{\"doubleField\":10000000}";
        TestDoubleNoScientificNotation actual = new TestDoubleNoScientificNotation(10000000);
        assertEquals(expected, JsonUtil.getInstance().writeValueAsString(actual));

        expected = "{\"doubleField\":99999999.99999}";
        actual = new TestDoubleNoScientificNotation(99999999.99999);
        assertEquals(expected, JsonUtil.getInstance().writeValueAsString(actual));

        expected = "{\"doubleField\":1000000000}";
        actual = new TestDoubleNoScientificNotation(1.0E9);
        assertEquals(expected, JsonUtil.getInstance().writeValueAsString(actual));

        expected = "{\"doubleField\":112345.6789123456}";
        actual = new TestDoubleNoScientificNotation(1.123456789123456E5);
        assertEquals(expected, JsonUtil.getInstance().writeValueAsString(actual));

        expected = "{\"doubleField\":100.0}";
        actual = new TestDoubleNoScientificNotation(100);
        assertEquals(expected, JsonUtil.getInstance().writeValueAsString(actual));
    }

    static class TestDoubleNoScientificNotation {
        @JsonSerialize(using = DoubleNoScientificNotationJsonSerializer.class)
        private double doubleField;

        public TestDoubleNoScientificNotation(double doubleField) {
            this.doubleField = doubleField;
        }
    }

}
