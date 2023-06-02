package org.lsj.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZipUtil {

    private static final int BUFFER = 1024;

    private GZipUtil() {}

    public static byte[] compress(String plain) throws IOException {
        byte[] output;

        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(plain.getBytes(StandardCharsets.UTF_8));
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {

            int count;
            byte[] data = new byte[BUFFER];
            while ((count = byteArrayInputStream.read(data, 0, BUFFER)) != -1) {
                gzipOutputStream.write(data, 0, count);
            }
            gzipOutputStream.finish();
            gzipOutputStream.flush();
            byteArrayOutputStream.flush();
            output = byteArrayOutputStream.toByteArray();
        }

        return output;
    }

    public static String decompress(byte[] compressData) throws IOException {
        byte[] output;
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressData);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream)) {

            int count;
            byte[] data = new byte[BUFFER];
            while ((count = gzipInputStream.read(data, 0, BUFFER)) != -1) {
                byteArrayOutputStream.write(data, 0, count);
            }
            byteArrayOutputStream.flush();
            output = byteArrayOutputStream.toByteArray();
        }

        return new String(output, StandardCharsets.UTF_8);
    }

}
