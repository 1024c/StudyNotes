package com.tawe.crowd.exception;

public class LoginAcctAlreadyInUseException extends RuntimeException {

    private String status;

    public String getStatus() {
        return status;
    }

    public LoginAcctAlreadyInUseException(String status) {
        this.status = status;
    }

    public LoginAcctAlreadyInUseException(String message, String status) {
        super(message);
        this.status = status;
    }


    public LoginAcctAlreadyInUseException(String message, Throwable cause, String status) {
        super(message, cause);
        this.status = status;
    }
}
