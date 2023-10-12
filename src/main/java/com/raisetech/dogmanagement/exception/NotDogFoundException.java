package com.raisetech.dogmanagement.exception;

public class NotDogFoundException extends RuntimeException{
    public NotDogFoundException() {
        super();
    }

    public NotDogFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotDogFoundException(String message) {
        super(message);
    }

    public NotDogFoundException(Throwable cause) {
        super(cause);
    }

}
