package parqueadero;

import exceptions.ParqueaderoLlenoException;
import exceptions.PlacaInvalidaException;
import vehiculos.Bicicleta;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ParqueaderoBicicletas {
    private static final int CAPACIDAD_MAXIMA = 10;
    private static final double TARIFA_HORA = 200;
    private final Map<String, Bicicleta> bicicletasEstacionadas = new HashMap<>();
    private int contadorBicicletas = 1;
    
    

    public void agregarVehiculo(Scanner scanner) throws ParqueaderoLlenoException {
        
        if (bicicletasEstacionadas.size() >= CAPACIDAD_MAXIMA) {
            throw new ParqueaderoLlenoException("Lo sentimos, no hay espacio, intente más tarde.");
        }

        String codigo = "BICI" + contadorBicicletas++;
        Bicicleta bicicleta = new Bicicleta();
        bicicletasEstacionadas.put(codigo, bicicleta);
        System.out.println("Bicicleta ingresada con código " + codigo + " a las " + bicicleta.getHoraEntrada());
        
    }
    
    
    //--------------------------------------------------------------------------------------------------------------------------//
    

    public void retirarVehiculo(Scanner scanner) throws PlacaInvalidaException  {
        System.out.print("Ingrese el código de la bicicleta: ");
        String codigo = scanner.nextLine().trim().toUpperCase();

        Bicicleta bicicleta = bicicletasEstacionadas.remove(codigo);
        
        if (bicicleta == null) {
            throw new PlacaInvalidaException("Lo sentimos, ese codigo de Bicicleta no esta registrado.");
        }

        LocalDateTime horaSalida = LocalDateTime.now();
        Duration duracion = Duration.between(bicicleta.getHoraEntrada(), horaSalida);
        long horas = Math.max(duracion.toHours(), 1);
        double costo = horas * TARIFA_HORA;

        System.out.println("Bicicleta retirada a las " + horaSalida);
        System.out.println("Tiempo: " + horas + " horas.");
        System.out.println("Valor a pagar: $" + costo);
    }

    
    //--------------------------------------------------------------------------------------------------------------------------//

    
    public void buscarVehiculo(Scanner scanner) throws PlacaInvalidaException {
        
        System.out.print("Ingrese el código de la bicicleta: ");
        String codigo = scanner.nextLine().trim().toUpperCase();

        Bicicleta bicicleta = bicicletasEstacionadas.get(codigo);
        
        if (bicicleta == null) {
            throw new PlacaInvalidaException("Lo sentimos, ese codigo de Bicicleta no esta registrado.");
        }

        LocalDateTime horaActual = LocalDateTime.now();
        Duration duracion = Duration.between(bicicleta.getHoraEntrada(), horaActual);
        long horas = Math.max(duracion.toHours(), 1);

        System.out.println("Bicicleta registrada a las " + bicicleta.getHoraEntrada());
        System.out.println("Tiempo transcurrido: " + horas + " horas.");
        
    }
}
