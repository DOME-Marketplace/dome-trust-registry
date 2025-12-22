package com.digitelts.dome.trust.registry.exceptions;

public class AuthException extends Exception {
    public AuthException() { super(); }
    public AuthException(String message) { super(message); }
    public AuthException(Throwable cause) { super(cause); }
    public AuthException(String message, Throwable cause) { super(message, cause); }
}

