package com.sajee.meetingmind.document.service;

import com.sajee.meetingmind.document.extractor.DocumentExtractor;
import com.sajee.meetingmind.exception.InvalidFileTypeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentExtractionServiceImpl implements DocumentExtractionService {

    private final List<DocumentExtractor> extractors;

    @Override
    public String extract(Path filePath, String contentType) {

        DocumentExtractor extractor = extractors.stream()
                .filter(e -> e.supports(contentType))
                .findFirst()
                .orElseThrow(() -> new InvalidFileTypeException(contentType));

        return extractor.extract(filePath);
    }
}
