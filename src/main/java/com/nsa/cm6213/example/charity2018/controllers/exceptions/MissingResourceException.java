package com.nsa.cm6213.example.charity2018.controllers.exceptions;

public class MissingResourceException extends RuntimeException {

    protected String returnUrl;

    public MissingResourceException(String msg, String aUrl) {
        super(msg);
        returnUrl = aUrl;
    }

    public MissingResourceException(String msg) {
        this(msg, "/");
    }

    public MissingResourceException() {
        this("Missing Resouce", "/");
    }
}
