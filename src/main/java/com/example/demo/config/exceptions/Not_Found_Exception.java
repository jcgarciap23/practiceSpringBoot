package com.example.demo.config.exceptions;

public class Not_Found_Exception extends RuntimeException{

    public Not_Found_Exception() {
    }

    public Not_Found_Exception(String message) {
        super(message);
    }
}
