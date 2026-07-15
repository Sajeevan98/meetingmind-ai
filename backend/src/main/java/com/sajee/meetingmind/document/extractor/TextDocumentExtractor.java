package com.sajee.meetingmind.document.extractor;

import com.sajee.meetingmind.exception.DocumentExtractionException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TextDocumentExtractor implements DocumentExtractor {

    @Override
    public boolean supports(String contentType) {

        return "text/plain".equals(contentType);
    }

    @Override
    public String extract(Path filePath) {

        try {

            return Files.readString(filePath);
        } catch (IOException ex) {

            throw new DocumentExtractionException("Failed to read text file.", ex);
        }
    }
}
