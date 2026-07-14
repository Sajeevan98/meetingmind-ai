package com.sajee.meetingmind.util.document.extractor;

import com.sajee.meetingmind.util.exception.DocumentExtractionException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DocxDocumentExtractor implements DocumentExtractor {

    @Override
    public boolean supports(String contentType) {
        return "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
                .equals(contentType);
    }

    @Override
    public String extract(Path filePath) {

        try (XWPFDocument document = new XWPFDocument(Files.newInputStream(filePath))) {

            XWPFWordExtractor extractor = new XWPFWordExtractor(document);

            return extractor.getText();

        } catch (IOException ex) {

            throw new DocumentExtractionException("Failed to extract text from DOCX.", ex);
        }
    }
}
