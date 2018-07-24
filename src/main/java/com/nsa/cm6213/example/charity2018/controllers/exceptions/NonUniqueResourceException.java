package com.nsa.cm6213.example.charity2018.controllers.exceptions;

public class NonUniqueResourceException extends RuntimeException {

    protected String returnUrl;

    public NonUniqueResourceException(String msg, String aUrl) {
        super(msg);
        returnUrl = aUrl;
    }

    public NonUniqueResourceException(String aUrl) {
        this("Missing Resource", aUrl);
    }

    public NonUniqueResourceException() {
        this("Missing Resouce", "/");
    }
}
