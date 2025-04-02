package com.gowri.blitz.exceptions;

/*
 * @author NaveenWodeyar
 * @date 28-03-2025
 */

public class BlitzException extends RuntimeException {

    private int errorCode;
    private String details;
    public BlitzException() {
        super();
    }

    public BlitzException(String message) {
        super(message);
    }

    public BlitzException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlitzException(Throwable cause) {
        super(cause);
    }

    public BlitzException(String message, Throwable cause, int errorCode, String details) {
        super(message, cause);
        this.errorCode = errorCode;
        this.details = details;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "BlitzException{" +
                "message='" + getMessage() + '\'' +
                ", errorCode=" + errorCode +
                ", details='" + details + '\'' +
                '}';
    }
}
