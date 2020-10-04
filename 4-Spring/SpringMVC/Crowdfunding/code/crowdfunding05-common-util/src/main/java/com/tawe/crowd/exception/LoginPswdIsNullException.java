package com.tawe.crowd.exception;

public class LoginPswdIsNullException extends RuntimeException {
    public LoginPswdIsNullException() {
    }

    public LoginPswdIsNullException(String message) {
        super(message);
    }

    public LoginPswdIsNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
