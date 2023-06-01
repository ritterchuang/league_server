package org.lsj.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DoubleNoScientificNotationJsonSerializerTest {

    @Test
    void GivenDouble_WhenSerialize_ThenReturnPlainString() throws IOException {
        final double value = 99999.99999D;
        final String expected = "99999.99999";
        JsonGenerator mockJsonGenerator = mock(JsonGenerator.class);

        new DoubleNoScientificNotationJsonSerializer().serialize(value, mockJsonGenerator, null);

        verify(mockJsonGenerator).writeNumber(expected);
    }

}
