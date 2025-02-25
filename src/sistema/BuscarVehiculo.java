package sistema;

import exceptions.PlacaInvalidaException;
import exceptions.TipoVehiculoNoPermitidoException;
import parqueadero.ParqueaderoCarros;
import parqueadero.ParqueaderoMotos;
import parqueadero.ParqueaderoBicicletas;
import java.util.Scanner;

public class BuscarVehiculo {
    private final Scanner scanner;
    private final ParqueaderoCarros parqueaderoCarros;
    private final ParqueaderoMotos parqueaderoMotos;
    private final ParqueaderoBicicletas parqueaderoBicicletas;

    public BuscarVehiculo(Scanner scanner, ParqueaderoCarros parqueaderoCarros, ParqueaderoMotos parqueaderoMotos, ParqueaderoBicicletas parqueaderoBicicletas) {
        this.scanner = scanner;
        this.parqueaderoCarros = parqueaderoCarros;
        this.parqueaderoMotos = parqueaderoMotos;
        this.parqueaderoBicicletas = parqueaderoBicicletas;
    }

    public void ejecutar() throws TipoVehiculoNoPermitidoException {
        System.out.println("\n--- BUSCAR VEHÍCULO ---");
        System.out.println("Ingrese tipo:\n 1. Carro\n 2. Moto\n 3. Bicicleta\n");
        String tipo = scanner.nextLine().trim().toLowerCase();

        try {
            switch (tipo) {
                case "carro":
                case "c":
                case "1":
                    parqueaderoCarros.buscarVehiculo(scanner);
                    break;
                case "moto":
                case "m":
                case "2":
                    parqueaderoMotos.buscarVehiculo(scanner);
                    break;
                case "bicicleta":
                case "b":
                case "3":
                    parqueaderoBicicletas.buscarVehiculo(scanner);
                    break;

                default:
                    System.out.println("❌ Tipo inválido. Intente de nuevo.");
                         
            } 
            
        } catch (PlacaInvalidaException e) {
                System.out.println(e.getMessage());
        }       
       
    }
}
