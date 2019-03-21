package org.mymusic.core.utils;

import org.mymusic.core.domain.UserFile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class FileUtils {

    public static HttpEntity<byte[]> toHttpEntity(UserFile file) {
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentLength(file.getBytes().length);
            headers.set(HttpHeaders.CONTENT_DISPOSITION,
                    String.format("attachment; filename=%s", encodeFilename(file.getFilename())));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error occurred: " + e.getMessage());
        }
        return new HttpEntity<>(file.getBytes(), headers);
    }

    private static String encodeFilename(String filename) throws UnsupportedEncodingException {
        return filename == null ? "file" : URLEncoder.encode(filename, "UTF-8");
    }
}
