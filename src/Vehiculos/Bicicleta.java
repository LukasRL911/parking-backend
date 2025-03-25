package com.parking.parqueadero;

import com.parking.Vehiculos.Vehiculo;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import com.parking.exceptions.ParqueaderoLlenoException;
import java.util.Scanner;

public class Bicicleta extends Parqueadero {

    private static int contador = 1;
    private String code;

    public Bicicleta() {
        super();
        this.code = ("BICI" + contador++).toUpperCase();  // Asegurarse que el código sea en mayúsculas
    }

    @Override
    public String getTipoVehiculo() {
        return "BICICLETA";
    }

    @Override
    public long obtenerCupoMaximoPermitidoPorTipoVehiculo() {
        return 2;
    }

    @Override
    public long obtenerPrecioParqueaderPorTipo() {
        return 200;
    }

    @Override
    public String getMensajeAgregar() {

        if (obtenerEspaciosDisponibles() <= 0) {
            throw new ParqueaderoLlenoException("Lo sentimos, no hay espacio para vehículos tipo " + getTipoVehiculo() + ", intente más tarde.");
        }

        System.out.println("Este es el código de la bicicleta: " + code);

        Vehiculo bicicleta = new Vehiculo(code, getTipoVehiculo());

        agregarVehiculo(bicicleta);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String horaIngreso = LocalTime.now().format(formatter);

        return getTipoVehiculo() + " agregada a las " + horaIngreso + ". Quedan " + obtenerEspaciosDisponibles() + " espacios disponibles.";
    }

    @Override
    public String getMensajeRetirar() {
        System.out.println("Este es el código de la bicicleta para retirar: ");
        Scanner scanner = new Scanner(System.in);
        String idtype = scanner.next().toUpperCase();  // Convertir a mayúsculas

        Vehiculo bicicleta = buscarVehiculo(idtype);

        if (bicicleta == null) {
            throw new PlacaInvalidaException("La bicicleta con el código " + idtype + " no está registrada en el parqueadero.");
        }

        String precio = String.valueOf(obtenerPrecioParqueadero(idtype));

        retirarVehiculo(idtype);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String horaIngreso = LocalTime.now().format(formatter);

        return getTipoVehiculo() + " retirada a las " + horaIngreso + ". Quedan " + obtenerEspaciosDisponibles() + " espacios disponibles. Debe pagar: " + precio;
    }

    @Override
    public String getMensajeBuscar() {
        System.out.println("Ingrese el código de la bicicleta para buscar: ");
        Scanner scanner = new Scanner(System.in);
        String idtype = scanner.next();

        Vehiculo bicicleta = buscarVehiculo(idtype);

        if (bicicleta == null) {
            throw new PlacaInvalidaException("La bicicleta con el código " + idtype + " no está registrada en el parqueadero.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String horaIngreso = LocalTime.now().format(formatter);

        long precio = obtenerPrecioParqueadero(idtype);
        return getTipoVehiculo() + " con código " + bicicleta.getIdtype() + " encontrada. Estado: Ingresada a las " + horaIngreso + ". Deuda pendiente: " + precio;
    }
}
