package com.sajee.meetingmind.util.exception;

public class FileSizeExceededException extends RuntimeException {

    public FileSizeExceededException(Long maxSize, Long fileSize) {

        super("Maximum size: " + maxSize + ", Uploaded file size: " + fileSize);
    }
}
