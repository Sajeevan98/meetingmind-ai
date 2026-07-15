package com.sajee.meetingmind.document.service;

import java.nio.file.Path;

public interface DocumentExtractionService {

    String extract(Path filePath, String contentType);
}
