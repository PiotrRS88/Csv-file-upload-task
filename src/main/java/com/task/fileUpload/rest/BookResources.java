package com.task.fileUpload.rest;

import com.task.fileUpload.entity.BookDetails;
import com.task.fileUpload.repository.BookRepository;
import com.task.fileUpload.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
public class BookResources {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @GetMapping("/book")
    public ResponseEntity<String> getBookById(@RequestParam("id") Long id) {

        List<BookDetails> result = bookRepository.findAllById(Collections.singleton(id));

        return new ResponseEntity<>(String.valueOf(result), HttpStatus.OK);
    }

    @PostMapping("/fetchBooks")
    public ResponseEntity<String> uploadData(@RequestParam("file") MultipartFile file) throws IOException {

        bookRepository.saveAll(bookService.extractDetails(file));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id") Long id) {

        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return ResponseEntity.ok("Book by id: " + id + " has been deleted.");
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
