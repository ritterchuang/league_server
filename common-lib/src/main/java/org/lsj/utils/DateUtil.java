package org.lsj.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    private static final String RABBITMQ_PUBLISH_DATE = "yyyy-MM-dd HH:mm:ss";

    private static final DateTimeFormatter localTimeZoneFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static DateTimeFormatter isoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private static SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getRabbitmqPublishDate(long timeStamp) {
        return new SimpleDateFormat(RABBITMQ_PUBLISH_DATE).format(timeStamp);
    }

    /**
     * 將long型別的時間戳，轉換成國際標準時間(UTC)的ISO格式字串，顯示到秒數小數點後三位(同JS的Web-Server端)。
     *
     * 輸出範例格式:
     * 2021-01-11T11:30:53.000Z
     *
     * @param timestamp 時間戳
     * @return ISO格式時間字串
     */
    public static String convertTimeStampToUTCISOString(long timestamp){
        return isoFormatter.withZone(ZoneId.from(ZoneOffset.UTC)).format(Instant.ofEpochMilli(timestamp));
    }

    /**
     * 將long型別的時間戳，轉換成yyyy-MM-dd HH:mm:ss格式的字串，時區為服務端的系統時區。
     * web-server的new Data()，預設抓的就是系統時間。
     *
     * 輸出範例格式:
     * 2021-10-08 23:11:38
     *
     * @param timestamp 時間戳
     * @return 預期格式的時間字串
     */
    public static String convertTimeStampToLocalTimeZoneFormatString(long timestamp){
        return localTimeZoneFormatter.withZone(ZoneOffset.systemDefault()).format(Instant.ofEpochMilli(timestamp));
    }

    /**
     * 將String 類型的國際標準時間(UTC)的ISO格式字串，轉換成Timestamp(long) 型態(Col:13)
     *
     * @param time
     * @return Timestamp(long) 格式的時間
     * @throws ParseException
     */
    public static Timestamp convertISOStringToLongTime(String time) throws ParseException {
        return new Timestamp(simpleDateFormat.parse(time).getTime());
    }

    public static Long convertISOStringToLong(String time) throws ParseException {
        Date d = simpleDateFormat2.parse(time);
        return d.getTime();
    }

}
