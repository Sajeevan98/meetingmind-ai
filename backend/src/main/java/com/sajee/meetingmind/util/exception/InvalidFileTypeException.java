package com.sajee.meetingmind.util.exception;

public class InvalidFileTypeException extends RuntimeException {

    public InvalidFileTypeException(String type) {
        super("Unsupported file type: " + type);
    }
}
