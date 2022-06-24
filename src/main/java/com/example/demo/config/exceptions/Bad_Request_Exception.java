package com.example.demo.config.exceptions;

public class Bad_Request_Exception extends RuntimeException {

    public Bad_Request_Exception() {
    }

    public Bad_Request_Exception(String message) {
        super(message);
    }
}
