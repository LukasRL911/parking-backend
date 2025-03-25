package com.parking.exceptions;

public class InvalidOption extends RuntimeException {

    public InvalidOption(String mensaje) {
        super(mensaje);
    }
}
