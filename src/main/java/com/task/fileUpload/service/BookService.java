package com.task.fileUpload.service;

import com.task.fileUpload.entity.BookDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookService {

    List<BookDetails> extractDetails(MultipartFile file) throws IOException;

}
