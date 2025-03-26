package com.evnit.ttpm.stdv.exception;



public class CustomAppException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int errorCode;

    public CustomAppException(String message) {
        super(message);
    }

    public CustomAppException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public CustomAppException(String message, Throwable cause) {
        super(message, cause);
    }
}