package com.task.fileUpload.service;

import com.task.fileUpload.entity.BookDetails;
import com.task.fileUpload.utils.ISO8601Validator;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvFormat;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    ISO8601Validator iso8601Validator;

    @Override
    public List<BookDetails> extractDetails(MultipartFile file) throws IOException {

        List<Record> parsedRecords = parseFileData(file);

        return parsedRecords.stream()
                .map(record -> {
                    BookDetails details = new BookDetails();
                    details.setName(record.getString("name"));
                    details.setDescription(record.getString("description"));
                    details.setUpdatedTimestamp(record.getString("updated_timestamp"));

                    try {
                        iso8601Validator.validateDate(record.getString("updated_timestamp"));
                        details.setUpdatedTimestamp(record.getString("updated_timestamp"));
                        return details;
                    } catch (Exception e) {
                        System.out.println("Date validator exception: " + details);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static List<Record> parseFileData(MultipartFile file) throws IOException {

        InputStream inputStream = file.getInputStream();
        CsvParserSettings settings = new CsvParserSettings();
        CsvFormat csvFormat = new CsvFormat();
        csvFormat.setDelimiter(";");
        settings.setFormat(csvFormat);
        settings.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(settings);
        return parser.parseAllRecords(inputStream);
    }
}
