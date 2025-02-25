package sistema;

import exceptions.ParqueaderoLlenoException;
import exceptions.PlacaInvalidaException;
import exceptions.TipoVehiculoNoPermitidoException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuParqueadero {
    private final Scanner scanner;
    private final AgregarVehiculo agregarVehiculo;
    private final RetirarVehiculo retirarVehiculo;
    private final BuscarVehiculo buscarVehiculo;

    public MenuParqueadero(Scanner scanner, AgregarVehiculo agregarVehiculo, RetirarVehiculo retirarVehiculo, BuscarVehiculo buscarVehiculo) {
        this.scanner = scanner;
        this.agregarVehiculo = agregarVehiculo;
        this.retirarVehiculo = retirarVehiculo;
        this.buscarVehiculo = buscarVehiculo;
    }

    public void mostrarMenu() throws PlacaInvalidaException {
        while (true) {
            System.out.println("\n--- MENÚ PARQUEADERO ---");
            System.out.println("1. Agregar vehículo");
            System.out.println("2. Retirar vehículo");
            System.out.println("3. Buscar vehículo");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            String opcion = scanner.nextLine().trim();
            switch (opcion) {
                case "1":
                {
                    try {
                        agregarVehiculo.ejecutar();
                    } catch (ParqueaderoLlenoException ex) {
                        Logger.getLogger(MenuParqueadero.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                case "2":
                    retirarVehiculo.ejecutar();
                    break;
                case "3":
                {
                    try {
                        buscarVehiculo.ejecutar();
                    } catch (TipoVehiculoNoPermitidoException ex) {
                        Logger.getLogger(MenuParqueadero.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                case "4":
                    System.out.println("Saliendo del sistema...");
                    return;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }
}
