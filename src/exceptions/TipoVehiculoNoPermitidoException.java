package com.parking.exceptions;

public class TipoVehiculoNoPermitidoException extends RuntimeException {

    public TipoVehiculoNoPermitidoException(String mensaje) {
        super(mensaje);
    }

}
