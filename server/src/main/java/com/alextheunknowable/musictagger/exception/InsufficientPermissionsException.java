package com.alextheunknowable.musictagger.exception;

public class InsufficientPermissionsException extends RuntimeException {
    public InsufficientPermissionsException() {
        super();
    }
    public InsufficientPermissionsException(String message) {
        super(message);
    }
    public InsufficientPermissionsException(String message, Exception cause) {
        super(message, cause);
    }
}
