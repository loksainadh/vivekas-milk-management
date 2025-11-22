package com.milk.vivekas.exception;

/**
 * Base unchecked exception for the application.
 */
public class VivekasException extends RuntimeException {
    public VivekasException(String message) { super(message); }
    public VivekasException(String message, Throwable cause) { super(message, cause); }
}
