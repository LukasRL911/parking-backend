package parqueadero;

import exceptions.ParqueaderoLlenoException;
import exceptions.PlacaInvalidaException;
import vehiculos.Carro;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ParqueaderoCarros {
    private static final int CAPACIDAD_MAXIMA = 10;
    private static final double TARIFA_HORA = 1000;
    private final Map<String, Carro> carrosEstacionados = new HashMap<>();
    
    
    //------------------------------------------------------------------------------------------------------------//

    
    public void agregarVehiculo(Scanner scanner) throws ParqueaderoLlenoException, PlacaInvalidaException {
        if (carrosEstacionados.size() >= CAPACIDAD_MAXIMA) {
            throw new ParqueaderoLlenoException("Lo sentimos, no hay espacio, intente más tarde.");
        }

        System.out.print("Ingrese la placa del carro: ");
        String placa = scanner.nextLine().trim().toUpperCase();

        if (!placa.matches("^[A-Z]{3}[0-9]{3}$")) {
            throw new PlacaInvalidaException("Error: Placa inválida debe seguir el formato de la region AAA000.");
        }
        
        if (carrosEstacionados.containsKey(placa)) {
            throw new PlacaInvalidaException("Error: Ya existe una Carro con esta placa.");

        }

        Carro carro = new Carro(placa);
        carrosEstacionados.put(placa, carro);
        System.out.println("Carro ingresado con la placa " + placa + " a las " + carro.getHoraEntrada());
    }

    
    
    //-------------------------------------------------------------------------------------------------------------//
    
    
    public void retirarVehiculo(Scanner scanner) throws PlacaInvalidaException {
        System.out.print("Ingrese la placa del carro: ");
        String placa = scanner.nextLine().trim().toUpperCase();

        Carro carro = carrosEstacionados.remove(placa);
        
        if (carro == null)  {
            throw new PlacaInvalidaException("Lo sentimos, esa placa Moto no esta registrada.");
        }

        LocalDateTime horaSalida = LocalDateTime.now();
        Duration duracion = Duration.between(carro.getHoraEntrada(), horaSalida);
        long horas = Math.max(duracion.toHours(), 1);
        double costo = horas * TARIFA_HORA;

        System.out.println("Carro retirado a las " + horaSalida);
        System.out.println("Tiempo: " + horas + " horas.");
        System.out.println("Valor a pagar: $" + costo);
        
    }
    
    
    //---------------------------------------------------------------------------------------------------------------//
    
    
    public void buscarVehiculo(Scanner scanner) throws PlacaInvalidaException {
        System.out.print("Ingrese la placa del carro: ");
        String placa = scanner.nextLine().trim().toUpperCase();

        Carro carro = carrosEstacionados.get(placa);
        
        if (carro == null) {
            throw new PlacaInvalidaException("Lo sentimos, esa placa no esta registrada.");
        }

        LocalDateTime horaActual = LocalDateTime.now();
        Duration duracion = Duration.between(carro.getHoraEntrada(), horaActual);
        long horas = Math.max(duracion.toHours(), 1);

        System.out.println("Carro registrado a las " + carro.getHoraEntrada());
        System.out.println("Tiempo transcurrido: " + horas + " horas.");
    }
}
