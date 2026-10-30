package com.github.dumbarudev.storage.Data;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressionManager {
    public static byte[] compressJson(String jsonString) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipOut = new GZIPOutputStream(baos)) {
            gzipOut.write(jsonString.getBytes(StandardCharsets.UTF_8));
        }
        return baos.toByteArray();
    }

    /**
     * Decompresses a GZIP byte array back into a plain JSON string.
     */
    public static String decompressJson(byte[] compressedBytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(compressedBytes);
        try (GZIPInputStream gzipIn = new GZIPInputStream(bais);
             BufferedReader reader = new BufferedReader(new InputStreamReader(gzipIn, StandardCharsets.UTF_8))) {

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        }
    }
    public static String fromFile(File file){
        Path path = Path.of(file.getPath());
        try {
            return decompressJson(Files.readAllBytes(path));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void toFile(File file,String json){
        String p = file.getPath();
        try {
            Files.write(Path.of(p),compressJson(json));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
