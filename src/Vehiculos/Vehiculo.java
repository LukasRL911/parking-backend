package com.parking.Vehiculos;

import java.time.LocalDateTime;

public class Vehiculo {

    private String idtype;
    private LocalDateTime horaEntrada;
    private String tipoVehiculo;


    public Vehiculo(String idtype, String tipoVehiculo) {

        this.idtype = idtype.toUpperCase();
        this.horaEntrada = LocalDateTime.now();
        this.tipoVehiculo = tipoVehiculo;

    }

    public String getIdtype() {

        return idtype;
    }

    public LocalDateTime getHoraEntrada() {

        return horaEntrada;
    }

    public String getTipoVehiculo() {

        return tipoVehiculo;
    }

}