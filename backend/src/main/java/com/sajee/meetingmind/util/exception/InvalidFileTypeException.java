package com.sajee.meetingmind.exception;

public class InvalidFileTypeException extends RuntimeException {

    public InvalidFileTypeException(String type) {
        super("Unsupported file type: " + type);
    }
}
