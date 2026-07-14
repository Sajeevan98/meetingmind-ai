package com.sajee.meetingmind.util.document.extractor;

import java.nio.file.Path;

public interface DocumentExtractor {

    boolean supports(String contentType);

    String extract(Path filePath);
}