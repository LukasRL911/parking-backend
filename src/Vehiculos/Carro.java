package com.parking.parqueadero;

import com.parking.Vehiculos.Vehiculo;
import com.parking.exceptions.ParqueaderoLlenoException;
import com.parking.exceptions.PlacaInvalidaException; // Importación de la excepción

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Carro extends Parqueadero {

    public static final String EXPRESION_REGULAR_PLACA = "^[A-Z]{3}[0-9]{3}$"; // Expresión regular para validar la placa

    public Carro() {
        super();
    }

    @Override
    public String getTipoVehiculo() {
        return "CARRO";
    }

    @Override
    public long obtenerCupoMaximoPermitidoPorTipoVehiculo() {
        return 3;
    }

    @Override
    public long obtenerPrecioParqueaderPorTipo() {
        return 1000;
    }


//---------------------------------------------------------------------------------------------------------------------------------------------------//


    @Override
    public String getMensajeAgregar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la placa del carro para agregar: ");
        String idtype = scanner.next();

        if (obtenerEspaciosDisponibles() <= 0) {
            throw new ParqueaderoLlenoException("Lo sentimos, no hay espacio para vehículos tipo " + getTipoVehiculo() + ", intente más tarde.");
        }

        if (buscarVehiculo(idtype) != null) {
            throw new PlacaInvalidaException("El vehículo con la placa " + idtype + " ya está registrado en el parqueadero.");
        }

        if (!idtype.matches(EXPRESION_REGULAR_PLACA)) {
            throw new PlacaInvalidaException("Placa de Carro inválida. Por favor, ingrese un código válido (ejemplo: ABC123).");
        }

        Vehiculo vehiculo = new Vehiculo(idtype, getTipoVehiculo());
        agregarVehiculo(vehiculo);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String horaIngreso = LocalTime.now().format(formatter);

        return getTipoVehiculo() + " agregado a las " + horaIngreso + ". Quedan " + obtenerEspaciosDisponibles() + " espacios disponibles.";
    }


//------------------------------------------------------------------------------------------------------------------------------------------------------------//


    @Override
    public String getMensajeRetirar() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Esta es la placa del carro para retirar: ");
        String idtype = scanner.next().toUpperCase();

        Vehiculo vehiculo = buscarVehiculo(idtype);

        if (vehiculo == null) {
            throw new PlacaInvalidaException("El vehículo con la placa " + idtype + " no está registrado en el parqueadero.");
        }

        long precio = obtenerPrecioParqueadero(idtype);

        retirarVehiculo(idtype);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String horaIngreso = LocalTime.now().format(formatter);

        return getTipoVehiculo() + " retirado a las " + horaIngreso + ". Quedan " + obtenerEspaciosDisponibles() + " espacios disponibles. Debe pagar: " + precio;
    }

    @Override
    public String getMensajeBuscar() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la placa del carro para buscar: ");
        String idtype = scanner.next();

        Vehiculo vehiculo = buscarVehiculo(idtype);

        if (vehiculo == null) {
            throw new PlacaInvalidaException("El vehículo con la placa " + idtype + " no está registrado en el parqueadero.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String horaIngreso = LocalTime.now().format(formatter);

        return "Carro con código " + idtype + " encontrado. Estado: Ingresado a las " + horaIngreso + ". Deuda pendiente: " + obtenerPrecioParqueadero(idtype);
    }
}
