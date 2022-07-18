package com.jentis.tracking.model;

public class JentisException  extends Exception{

    String message;
    public JentisException() {
        super();
    }

    public JentisException(String message, Throwable cause) {
        super(message, cause);

        this.message = message;
    }

    public JentisException(String message) {
        this.message = message;
    }
}
