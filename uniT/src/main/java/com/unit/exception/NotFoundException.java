package com.unit.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("User not found !");
    }

}
