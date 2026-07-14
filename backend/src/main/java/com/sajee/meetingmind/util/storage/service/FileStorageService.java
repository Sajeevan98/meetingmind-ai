package com.sajee.meetingmind.util.storage.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String storeFile(MultipartFile file);
}
