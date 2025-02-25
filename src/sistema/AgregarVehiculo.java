package sistema;

import parqueadero.ParqueaderoCarros;
import parqueadero.ParqueaderoMotos;
import exceptions.ParqueaderoLlenoException;
import exceptions.PlacaInvalidaException;
import exceptions.TipoVehiculoNoPermitidoException;
import parqueadero.ParqueaderoBicicletas;
import java.util.Scanner;

public class AgregarVehiculo {
    private final Scanner scanner;
    private final ParqueaderoCarros parqueaderoCarros;
    private final ParqueaderoMotos parqueaderoMotos;
    private final ParqueaderoBicicletas parqueaderoBicicletas;

    public AgregarVehiculo(Scanner scanner, ParqueaderoCarros parqueaderoCarros, ParqueaderoMotos parqueaderoMotos, ParqueaderoBicicletas parqueaderoBicicletas) {
        this.scanner = scanner;
        this.parqueaderoCarros = parqueaderoCarros;
        this.parqueaderoMotos = parqueaderoMotos;
        this.parqueaderoBicicletas = parqueaderoBicicletas;
    }

    public void ejecutar() throws PlacaInvalidaException, ParqueaderoLlenoException {
        System.out.println("\n--- AGREGAR VEHÍCULO ---");
        System.out.println("Ingrese tipo:\n 1. Carro\n 2. Moto\n 3. Bicicleta\n");
        String tipo = scanner.nextLine().trim().toLowerCase();
        
        try {
            switch (tipo) {
                
   
                case "carro":
                case "c":
                case "1":
                    parqueaderoCarros.agregarVehiculo(scanner);
                    break;

                case "moto":
                case "m":
                case "2":
                    parqueaderoMotos.agregarVehiculo(scanner);
                    break;

                case "bicicleta":
                case "b":
                case "3":
                    parqueaderoBicicletas.agregarVehiculo(scanner);
                    break;

                default:
                    System.out.println("❌ Tipo inválido. Intente de nuevo.");
            }
            
        } catch (ParqueaderoLlenoException  | PlacaInvalidaException e) {

            System.out.println(e.getMessage());
        }
    }
}
