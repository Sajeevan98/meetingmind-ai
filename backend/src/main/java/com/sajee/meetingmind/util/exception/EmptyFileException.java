package com.sajee.meetingmind.util.exception;

public class EmptyFileException extends RuntimeException {

    public EmptyFileException() {
        super("Uploaded file is empty!");
    }
}
