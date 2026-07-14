package com.sajee.meetingmind.util.storage.service;

import com.sajee.meetingmind.util.exception.EmptyFileException;
import com.sajee.meetingmind.util.exception.FileSizeExceededException;
import com.sajee.meetingmind.util.exception.FileStorageException;
import com.sajee.meetingmind.util.exception.InvalidFileTypeException;
import com.sajee.meetingmind.util.storage.config.FileStorageProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImp implements FileStorageService {

    private final FileStorageProperties properties;

    @Override
    public String storeFile(MultipartFile file) {

        validateFile(file);
        return saveFile(file);
    }

    private void validateFile(MultipartFile file) {

        if (file.isEmpty())
            throw new EmptyFileException();

        if (file.getSize() > properties.getMaxSize())
            throw new FileSizeExceededException(properties.getMaxSize(), file.getSize());

        if (!properties.getAllowedTypes().contains(file.getContentType()))
            throw new InvalidFileTypeException(file.getContentType());
    }

    private String saveFile(MultipartFile file) {

        try {
            Path uploadPath = Paths.get(properties.getUploadDir())
                    .toAbsolutePath()
                    .normalize();

            Files.createDirectories(uploadPath);

            String originalFilename = StringUtils.cleanPath(
                    Objects.requireNonNull(file.getOriginalFilename())
            );

            String extension = "";

            int index = originalFilename.lastIndexOf(".");

            if (index > 0) {
                extension = originalFilename.substring(index); // .pdf, .doc, .jpg
            }

            String storedFilename = UUID.randomUUID() + extension;

            Path targetLocation = uploadPath.resolve(storedFilename);

//            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(file.getInputStream(), targetLocation);

            return storedFilename;

        } catch (IOException ex) {

            throw new FileStorageException("Failed to store file.", ex);
        }
    }
}
