package sistema; 

import exceptions.ParqueaderoLlenoException;
import exceptions.PlacaInvalidaException;
import parqueadero.ParqueaderoCarros;
import parqueadero.ParqueaderoMotos;
import parqueadero.ParqueaderoBicicletas;
import exceptions.TipoVehiculoNoPermitidoException;
import java.util.Scanner;

public class RetirarVehiculo {
    private final Scanner scanner;
    private final ParqueaderoCarros parqueaderoCarros;
    private final ParqueaderoMotos parqueaderoMotos;
    private final ParqueaderoBicicletas parqueaderoBicicletas;

    public RetirarVehiculo(Scanner scanner, ParqueaderoCarros parqueaderoCarros, ParqueaderoMotos parqueaderoMotos, ParqueaderoBicicletas parqueaderoBicicletas) {
        this.scanner = scanner;
        this.parqueaderoCarros = parqueaderoCarros;
        this.parqueaderoMotos = parqueaderoMotos;
        this.parqueaderoBicicletas = parqueaderoBicicletas;
    }

        public void ejecutar() throws PlacaInvalidaException {
            System.out.println("\n--- RETIRAR VEHÍCULO ---");
            System.out.println("Ingrese tipo:\n 1. Carro\n 2. Moto\n 3. Bicicleta\n");
            String tipo = scanner.nextLine().trim().toLowerCase();

            
            try {

                switch (tipo) {
                    case "carro":
                    case "c":
                    case "1":
                        parqueaderoCarros.retirarVehiculo(scanner);
                        break;
                    case "moto":
                    case "m":
                    case "2":
                        parqueaderoMotos.retirarVehiculo(scanner);
                        break;
                    case "bicicleta":
                    case "b":
                    case "3":
                        parqueaderoBicicletas.retirarVehiculo(scanner);
                        break;

                    default:
                        System.out.println("❌ Tipo inválido. Intente de nuevo.");
                }

            } catch (PlacaInvalidaException e) {
                System.out.println(e.getMessage());
            }    
            
    }
}
