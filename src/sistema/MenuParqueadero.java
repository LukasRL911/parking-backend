package com.parking.sistema;

import com.parking.exceptions.InvalidOption;
import com.parking.exceptions.ParqueaderoLlenoException;
import com.parking.exceptions.PlacaInvalidaException;
import com.parking.exceptions.TipoVehiculoNoPermitidoException;
import com.parking.sistema.Operations;

import java.util.Scanner;

public class MenuParqueadero {

    public static final String EXPRESION_REGULAR_PLACA = "^[A-Z]{3}[0-9]{3}$";
    private final Scanner scanner;
    private final Operations operations;

    public MenuParqueadero() {
        this.scanner = new Scanner(System.in);
        this.operations = new Operations();
    }

    public void mostrarMenu() {

        while (true) {

            System.out.println("\n--- MENÚ PARQUEADERO ---");
            System.out.println("1. Agregar vehículo");
            System.out.println("2. Retirar vehículo");
            System.out.println("3. Buscar vehículo");
            System.out.println("4. Top 5 vehículos con mayor deuda");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine().trim();

            switch (opcion) {

                case "1":
                case "agregar":
                    try {
                        operations.MenuAddVehicle();
                    } catch (PlacaInvalidaException | ParqueaderoLlenoException | TipoVehiculoNoPermitidoException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case "2":
                case "retirar":
                    try {
                        operations.MenuRemoveVehicle();
                    } catch (PlacaInvalidaException | ParqueaderoLlenoException | TipoVehiculoNoPermitidoException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case "3":
                case "buscar":

                    try {
                        operations.MenuFindVehicle();
                    } catch (PlacaInvalidaException | ParqueaderoLlenoException | TipoVehiculoNoPermitidoException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case "4":
                case "top":

                    try {

                        operations.showTop5VehiclesWithTheMostDebt();
                    } catch (InvalidOption ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case "5":
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción inválida. Intente de nuevo.");

            }
        }
    }
}
