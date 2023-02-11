package com.task.fileUpload.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class ISO8601ValidatorTest {

    @Autowired
    ISO8601Validator iso8601Validator;

    @Test
    void should_validate_correct_date_with_no_exception() {

        //given
        String date = "2000-10-31T01:30:00.000-05:00";
        //when
        //then
        assertDoesNotThrow(() -> iso8601Validator.validateDate(date));
    }

    @Test
    void should_validate_wrong_format_date_and_throw_exception() {

        //given
        String date = "2000-10-31T01:30:00.000-05:0";
        //when
        //then
        assertThrows(RuntimeException.class, () -> iso8601Validator.validateDate(date));
    }
}