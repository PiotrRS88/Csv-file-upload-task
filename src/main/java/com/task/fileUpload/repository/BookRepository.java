package com.task.fileUpload.repository;

import com.task.fileUpload.entity.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookDetails, Long> {
}
