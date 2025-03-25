package com.parking.exceptions;

public class ParqueaderoLlenoException extends RuntimeException {
    
    public ParqueaderoLlenoException(String mensaje) {
        super(mensaje);
    }

}
