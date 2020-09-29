package com.tawe.crowd.exception;

/**
 * @ClassName LoginFailedException
 * @Description TODO
 * @Author Administrator
 * @Date 9/24/2020 6:14 PM
 * @Version 1.0
 **/
public class LoginFailedException extends RuntimeException{



    public LoginFailedException() {
        super();
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
