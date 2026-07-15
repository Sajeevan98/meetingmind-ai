package com.sajee.meetingmind.exception;

public class EmptyFileException extends RuntimeException {

    public EmptyFileException() {
        super("Uploaded file is empty!");
    }
}
