package com.parking.parqueadero;

import com.parking.Vehiculos.Vehiculo;
import com.parking.exceptions.InvalidOption;
import com.parking.exceptions.ParqueaderoLlenoException;
import com.parking.exceptions.PlacaInvalidaException; // Importación de la excepción

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Moto extends Parqueadero {

    public static final String EXPRESION_REGULAR_PLACA = "^[A-Z]{3}[0-9]{3}$";

    public Moto() {
        super();
    }

    @Override
    public String getTipoVehiculo() {
        return "MOTO";
    }

    @Override
    public long obtenerCupoMaximoPermitidoPorTipoVehiculo() {
        return 2;
    }

    @Override
    public long obtenerPrecioParqueaderPorTipo() {
        return 500;
    }

    @Override
    public String getMensajeAgregar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la placa de la moto para agregar: ");
        String idtype = scanner.next();

        if (obtenerEspaciosDisponibles() <= 0) {
            throw new ParqueaderoLlenoException("Lo sentimos, no hay espacio para vehículos tipo " + getTipoVehiculo() + ", intente más tarde.");
        }

        if (buscarVehiculo(idtype) != null) {
            throw new PlacaInvalidaException("El vehículo con la placa " + idtype + " ya está registrado en el parqueadero.");
        }

        if (!idtype.matches(EXPRESION_REGULAR_PLACA)) {
            throw new PlacaInvalidaException("Placa de Moto inválida. Por favor, ingrese un código válido (ejemplo: ABC123).");
        }

        Vehiculo moto = new Vehiculo(idtype, getTipoVehiculo());
        agregarVehiculo(moto);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String horaIngreso = LocalTime.now().format(formatter);

        return getTipoVehiculo() + " Agregada a las " + horaIngreso + ". Quedan " + obtenerEspaciosDisponibles() + " espacios disponibles.";
    }

    @Override
    public String getMensajeRetirar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Esta es la placa de la moto para retirar: ");
        String idtype = scanner.next().toUpperCase();

        Vehiculo moto = buscarVehiculo(idtype);

        if (moto == null) {
            throw new PlacaInvalidaException("El vehículo con la placa " + idtype + " no está registrado en el parqueadero.");
        }

        long precio = obtenerPrecioParqueadero(idtype);

        retirarVehiculo(idtype);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String horaIngreso = LocalTime.now().format(formatter);

        return getTipoVehiculo() + " retirada a las " + horaIngreso + ". Quedan " + obtenerEspaciosDisponibles() + " espacios disponibles. Debe pagar: " + precio;
    }

    @Override
    public String getMensajeBuscar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la placa de la moto para buscar: ");
        String idtype = scanner.next();

        Vehiculo moto = buscarVehiculo(idtype);
        if (moto == null) {
            throw new PlacaInvalidaException("La moto con la placa " + idtype + " no está registrada en el parqueadero.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String horaIngreso = LocalTime.now().format(formatter);

        return "Moto con código " + idtype + " encontrada. Estado: Ingresada a las " + horaIngreso + ". Deuda pendiente: " + obtenerPrecioParqueadero(idtype);
    }
}
