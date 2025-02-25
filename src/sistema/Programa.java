package sistema;

import exceptions.PlacaInvalidaException;
import exceptions.TipoVehiculoNoPermitidoException;
import parqueadero.ParqueaderoCarros;
import parqueadero.ParqueaderoMotos;
import parqueadero.ParqueaderoBicicletas;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) throws PlacaInvalidaException, TipoVehiculoNoPermitidoException {
        Scanner scanner = new Scanner(System.in);
        
        // Crear instancias de los parqueaderos
        ParqueaderoCarros parqueaderoCarros = new ParqueaderoCarros();
        ParqueaderoMotos parqueaderoMotos = new ParqueaderoMotos();
        ParqueaderoBicicletas parqueaderoBicicletas = new ParqueaderoBicicletas();

        // Crear instancias de las acciones, pasándoles los parqueaderos 
        AgregarVehiculo agregarVehiculo = new AgregarVehiculo(scanner, parqueaderoCarros, parqueaderoMotos, parqueaderoBicicletas);
        RetirarVehiculo retirarVehiculo = new RetirarVehiculo(scanner, parqueaderoCarros, parqueaderoMotos, parqueaderoBicicletas);
        BuscarVehiculo buscarVehiculo = new BuscarVehiculo(scanner, parqueaderoCarros, parqueaderoMotos, parqueaderoBicicletas);

        // Pasar las acciones al menú
        MenuParqueadero menu = new MenuParqueadero(scanner, agregarVehiculo, retirarVehiculo, buscarVehiculo);
        menu.mostrarMenu();
    }
}

