package org.lsj.utils;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateUtilTest {

    @Test
    void GivenTimestamp_WhenGetRabbitmqPublishDate_ThenReturnFormatDate() {
        final String dateFormat = "yyyy-MM-dd HH:mm:ss";
        final long timestamp = System.currentTimeMillis();
        final String expected = new SimpleDateFormat(dateFormat).format(timestamp);

        final String actual = DateUtil.getRabbitmqPublishDate(timestamp);

        assertEquals(expected, actual);
    }

    @Test
    void GivenTimestamp_WhenConvertTimeStampToISOString_ThenReturnFormatDate() {
        final String dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
        final long timestamp = System.currentTimeMillis();
        Instant instant = Instant.ofEpochMilli(timestamp);
        final String expected = DateTimeFormatter.ofPattern(dateFormat).withZone(ZoneId.from(ZoneOffset.UTC)).format(instant);

        final String actual = DateUtil.convertTimeStampToUTCISOString(timestamp);

        assertEquals(expected, actual);
    }

    @Test
    void GivenTimestamp_WhenConvertTimeStampToLocalTimeZoneFormatString_ThenReturnFormatDateTime() {
        final String dateFormat = "yyyy-MM-dd HH:mm:ss";
        final long timestamp = System.currentTimeMillis();
        Instant instant = Instant.ofEpochMilli(timestamp);
        final String expected = DateTimeFormatter.ofPattern(dateFormat).withZone(ZoneOffset.systemDefault()).format(instant);

        final String actual = DateUtil.convertTimeStampToLocalTimeZoneFormatString(timestamp);

        assertEquals(expected, actual);
    }

}
