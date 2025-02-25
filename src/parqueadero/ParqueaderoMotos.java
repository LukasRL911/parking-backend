package parqueadero;

import exceptions.ParqueaderoLlenoException;
import exceptions.PlacaInvalidaException;
import vehiculos.Moto;
import java.time.Duration; 
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ParqueaderoMotos {
    private static final int CAPACIDAD_MAXIMA = 5;
    private static final double TARIFA_HORA = 500;
    private final Map<String, Moto> motosEstacionadas = new HashMap<>();

    
    
    public void agregarVehiculo(Scanner scanner) throws ParqueaderoLlenoException, PlacaInvalidaException {
        if (motosEstacionadas.size() >= CAPACIDAD_MAXIMA) {
            throw new ParqueaderoLlenoException("Lo sentimos, no hay espacio, intente más tarde.");
        }
        
        System.out.print("Ingrese la placa de la moto: ");
        String placa = scanner.nextLine().trim().toUpperCase();

        if (!placa.matches("^[A-Z]{3}[0-9]{3}$")) {
            throw new PlacaInvalidaException("Error: Placa inválida debe seguir el formato de la region AAA000.");
        }
        
        if (motosEstacionadas.containsKey(placa)) {
            System.out.println("Error: Ya existe una Carro con esta placa.");
            return;
        }

        Moto moto = new Moto(placa);
        motosEstacionadas.put(placa, moto);
        System.out.println("Moto ingresada con la placa " + placa + " a las " + moto.getHoraEntrada());
    }

    
    
    //--------------------------------------------------------------------------------------------------------------------------//
    
    
    
    public void retirarVehiculo(Scanner scanner) throws PlacaInvalidaException {
        System.out.print("Ingrese la placa de la moto: ");
        String placa = scanner.nextLine().trim().toUpperCase();

        Moto moto = motosEstacionadas.remove(placa);
        
        if (moto == null) {
            throw new PlacaInvalidaException("Lo sentimos, esa placa Carro no esta registrada.");
        }
        
        LocalDateTime horaSalida = LocalDateTime.now();
        Duration duracion = Duration.between(moto.getHoraEntrada(), horaSalida);
        long horas = Math.max(duracion.toHours(), 1);
        double costo = horas * TARIFA_HORA;

        System.out.println("Moto retirada a las " + horaSalida);
        System.out.println("Tiempo: " + horas + " horas.");
        System.out.println("Valor a pagar: $" + costo);
    }
    
    
    
    //---------------------------------------------------------------------------------------------------------------------------------//
    
    
    public void buscarVehiculo(Scanner scanner) throws PlacaInvalidaException {
        System.out.print("Ingrese la placa de la moto: ");
        String placa = scanner.nextLine().trim().toUpperCase();

        Moto moto = motosEstacionadas.get(placa);
        if (moto == null) {
            throw new PlacaInvalidaException("Lo sentimos, esa placa no esta registrada.");
        }

        LocalDateTime horaActual = LocalDateTime.now();
        Duration duracion = Duration.between(moto.getHoraEntrada(), horaActual);
        long horas = Math.max(duracion.toHours(), 1);

        System.out.println("Moto registrada a las " + moto.getHoraEntrada());
        System.out.println("Tiempo transcurrido: " + horas + " horas.");
    }
}