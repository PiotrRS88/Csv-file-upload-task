package com.task.fileUpload.utils;

import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Configuration
public class ISO8601Validator {

    private static final String ISO8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    public void validateDate(String date) {

        SimpleDateFormat format = new SimpleDateFormat(ISO8601_FORMAT);
        try {
            format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("Wrong updated_timestamp format.");
        }
    }
}
