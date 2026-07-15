package com.sajee.meetingmind.document.extractor;

import com.sajee.meetingmind.exception.DocumentExtractionException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;

@Service
public class PdfDocumentExtractor implements DocumentExtractor {

    @Override
    public boolean supports(String contentType) {
        return "application/pdf".equals(contentType);
    }

    @Override
    public String extract(Path filePath) {

        try (PDDocument document = Loader.loadPDF(filePath.toFile())) {

            PDFTextStripper stripper = new PDFTextStripper();

            return stripper.getText(document);

        } catch (IOException ex) {

            throw new DocumentExtractionException("Failed to extract text from PDF.", ex);
        }
    }
}
