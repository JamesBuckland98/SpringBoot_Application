package com.nsa.cm6213.example.charity2018.controllers.exceptions;

public class MissingResourceException extends RuntimeException {

    protected String returnUrl;

    public MissingResourceException(String msg, String aUrl) {
        super(msg);
        returnUrl = aUrl;
    }

    public MissingResourceException(String aUrl) {
        this("Missing Resource", aUrl);
    }

    public MissingResourceException() {
        this("Missing Resouce", "/");
    }
}
