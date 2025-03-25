package com.parking.sistema;

import com.parking.Vehiculos.Vehiculo;
import com.parking.exceptions.TipoVehiculoNoPermitidoException;
import com.parking.parqueadero.Bicicleta;
import com.parking.parqueadero.Carro;
import com.parking.parqueadero.Moto;
import com.parking.parqueadero.Parqueadero;
import java.util.Scanner;
import java.util.List;

public class Operations {


    public static final String EXPRESION_REGULAR_PLACA = "^[A-Z]{3}[0-9]{3}$";
    private final Scanner scanner;
    private Parqueadero parqueadero;

    public Operations() {
        this.scanner = new Scanner(System.in);
    }



//------------------------------------------------------------------------------------------------------------------------//

    public void MenuAddVehicle() {
        System.out.println("\n--- AGREGAR VEHÍCULO ---");
        System.out.println("Ingrese tipo:\n 1. Carro\n 2. Moto\n 3. Bicicleta\n");

        String tipo = scanner.nextLine().trim().toLowerCase();

        parqueadero = ResolveParking(tipo);

        System.out.println(parqueadero.getMensajeAgregar());
    }


//------------------------------------------------------------------------------------------------------------------------//

    public void MenuRemoveVehicle() {

        System.out.println("\n--- RETIRAR VEHÍCULO ---");
        System.out.println("Ingrese tipo:\n 1. Carro\n 2. Moto\n 3. Bicicleta\n");

        String tipo = scanner.nextLine().trim().toLowerCase();
        parqueadero = ResolveParking(tipo);

        System.out.println(parqueadero.getMensajeRetirar());

    }


//------------------------------------------------------------------------------------------------------------------------//

    public void MenuFindVehicle() {

        System.out.println("\n--- BUSCAR VEHÍCULO ---");
        System.out.println("Ingrese tipo:\n 1. Carro\n 2. Moto\n 3. Bicicleta\n");

        String tipo = scanner.nextLine().trim().toLowerCase();
        parqueadero = ResolveParking(tipo);

        System.out.println(parqueadero.getMensajeBuscar());

    }

//------------------------------------------------------------------------------------------------------------------------//

    public void showTop5VehiclesWithTheMostDebt() {

        if (parqueadero == null) {
            System.out.println("No hay vehículos registrados en el parqueadero.");
            return;
        }

        System.out.println("Top 5 vehículos con mayor deuda:");

        List<Vehiculo> top5Vehiculos = parqueadero.obtenerTop5VehiculosConMasDeuda();

        if (top5Vehiculos.isEmpty()) {
            System.out.println("No hay vehículos con deuda en el parqueadero.");
        } else {
            for (Vehiculo vehiculo : top5Vehiculos) {
                long deuda = parqueadero.obtenerPrecioParqueadero(vehiculo.getIdtype());
                System.out.println("Vehículo ID: " + vehiculo.getIdtype() +
                        " - Deuda: " + deuda);
            }
        }
    }
//------------------------------------------------------------------------------------------------------------------------//



    private Parqueadero ResolveParking(String tipo) {

        switch (tipo) {

            case "1":
            case "carro":
                return new Carro();

            case "2":
            case "moto":
                return new Moto();

            case "3":
            case "bicicleta":
                return new Bicicleta();

            default:
                throw new TipoVehiculoNoPermitidoException("El tipo " + tipo + " de vehículo no está permitido.");

        }
    }

}
