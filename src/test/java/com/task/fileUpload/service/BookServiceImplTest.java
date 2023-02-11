package com.task.fileUpload.service;

import com.task.fileUpload.entity.BookDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class BookServiceImplTest {

    @Autowired
    BookService bookService;

    @Test
    void should_extract_two_books_with_correct_timestamp_format() throws IOException {

        //given
        MultipartFile multipartFile = new MockMultipartFile("correctTimestamp.csv", new FileInputStream("src/test/resources/correctTimestamp.csv"));

        //when
        List<BookDetails> extractedBooks = bookService.extractDetails(multipartFile);

        //then
        Assertions.assertEquals(extractedBooks.size(), 2);
    }

    @Test
    void should_extract_one_book_with_correct_timestamp_format() throws IOException {

        //given
        MultipartFile multipartFile = new MockMultipartFile("oneTimestampIncorrect.csv", new FileInputStream("src/test/resources/oneTimestampIncorrect.csv"));

        //when
        List<BookDetails> extractedBooks = bookService.extractDetails(multipartFile);

        //then
        Assertions.assertEquals(extractedBooks.size(), 1);
    }
}