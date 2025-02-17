package sistema;

import fabricas.Parqueadero;
import fabricas.ParqueaderoCarros;
import fabricas.ParqueaderoMotos;
import fabricas.ParqueaderoBicicletas;
import vehiculos.Vehiculo;
import vehiculos.Carro;
import vehiculos.Moto;
import vehiculos.Bicicleta;
import exceptions.ParqueaderoLlenoException;
import exceptions.TipoVehiculoNoPermitidoException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SistemaParqueadero {

    private static final int MAX_CARROS = 10;
    private static final int MAX_MOTOS = 5;
    private static final int MAX_BICICLETAS = 10;
    
    private static int carrosRegistrados = 0;
    private static int motosRegistradas = 0;
    private static int bicicletasRegistradas = 0;
    
    private static Map<String, Vehiculo> vehiculosRegistrados = new HashMap<>();
    
    private static int contadorBicicleta = 1;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        Parqueadero parqueaderoFabrica = null;
        
        while (true) {
            System.out.println(" SISTEMA DE PARQUEADERO ");
            System.out.println("1️⃣ Ingresar un vehículo");
            System.out.println("2️⃣ Retirar un vehículo");
            System.out.println("3️⃣ Buscar un vehículo por placa");
            System.out.println("4️⃣ Salir");
            System.out.print("Seleccione una opción: ");
            
            String opcionStr = scanner.nextLine().trim();
            try {
                opcion = Integer.parseInt(opcionStr);
            } catch (NumberFormatException e) {
                System.out.println("❌ Opción inválida. Por favor ingrese un número.");
                continue;
            }
            
            switch (opcion) {
                case 1: // Ingresar un vehículo
                    System.out.println("Seleccione el tipo de vehículo a ingresar:");
                    System.out.println("Ingrese el nombre completo o inicial (Carro, Moto, Bicicleta) o el número:");
                    String tipo = scanner.nextLine().toLowerCase();
                    
                    try {
                        if (tipo.equalsIgnoreCase("carro") || tipo.equals("1") || tipo.equalsIgnoreCase("c")) {
                            if (carrosRegistrados >= MAX_CARROS) {
                                throw new ParqueaderoLlenoException("No hay cupo disponible para carros.");
                            }
                            parqueaderoFabrica = new ParqueaderoCarros();
                        } else if (tipo.equalsIgnoreCase("moto") || tipo.equals("2") || tipo.equalsIgnoreCase("m")) {
                            if (motosRegistradas >= MAX_MOTOS) {
                                throw new ParqueaderoLlenoException("No hay cupo disponible para motos.");
                            }
                            parqueaderoFabrica = new ParqueaderoMotos(); 
                        } else if (tipo.equalsIgnoreCase("bicicleta") || tipo.equals("3") || tipo.equalsIgnoreCase("b")) {
                            if (bicicletasRegistradas >= MAX_BICICLETAS) {
                                throw new ParqueaderoLlenoException("No hay cupo disponible para bicicletas.");
                            }
                            parqueaderoFabrica = new ParqueaderoBicicletas();

                            String placaBici = "BICI_" + contadorBicicleta;
                            contadorBicicleta++;
                            bicicletasRegistradas++;
                            System.out.println("Este es tu número de bicicleta: " + placaBici);
                            Vehiculo bici = parqueaderoFabrica.crearVehiculo(placaBici);
                            bici.registrarEntrada();
                            vehiculosRegistrados.put(placaBici, bici);
                            System.out.println("Bicicleta con número " + placaBici + " registrada a las " + bici.getHoraIngreso());
                            continue;
                        } else {
                            throw new TipoVehiculoNoPermitidoException("Tipo de vehículo no permitido.");
                        }
                    } catch (ParqueaderoLlenoException | TipoVehiculoNoPermitidoException e) {
                        System.out.println("❌ Error: " + e.getMessage());
                        continue;
                    }
                    
                    try {
                        System.out.println("Ingrese la placa del vehículo (AAA123): ");
                        String placa = scanner.nextLine().toUpperCase();
                        if (vehiculosRegistrados.containsKey(placa)) {
                            System.out.println("❌ El vehículo con esa placa ya está registrado.");
                            continue;
                        }
                        Vehiculo vehiculo = parqueaderoFabrica.crearVehiculo(placa);
                        vehiculo.registrarEntrada();
                        vehiculosRegistrados.put(placa, vehiculo);
                        if (vehiculo instanceof Carro) {
                            carrosRegistrados++;
                        } else if (vehiculo instanceof Moto) {
                            motosRegistradas++;
                        }
                        System.out.println(vehiculo.getClass().getSimpleName() + " con placa " + placa + " registrado a las " + vehiculo.getHoraIngreso());
                    } catch (Exception e) {
                        System.out.println("❌ Error: " + e.getMessage());
                    }
                    break;
                    
                case 2: // Retirar un vehículo
                    System.out.println("Ingrese la placa o número de bicicleta del vehículo a retirar: ");
                    String placaRetiro = scanner.nextLine().toUpperCase();
                    
                    if (!vehiculosRegistrados.containsKey(placaRetiro)) {
                        System.out.println("❌ Vehículo no encontrado.");
                        continue;
                    }
                    
                    Vehiculo vehiculoRetirado = vehiculosRegistrados.get(placaRetiro);
                    LocalDateTime horaIngreso = vehiculoRetirado.getHoraIngreso();
                    long horasEstacionado = Duration.between(horaIngreso, LocalDateTime.now()).toHours();
                    if (horasEstacionado == 0) {
                        horasEstacionado = 1;
                    }
                    int tarifa = vehiculoRetirado.calcularTarifa();
                    int totalPagar = (int)(tarifa * horasEstacionado);
                    System.out.println("Vehículo con placa " + placaRetiro + " retirado exitosamente.");
                    System.out.println("Total a pagar: $" + totalPagar);
                    vehiculosRegistrados.remove(placaRetiro);
                    break;
                    
                case 3: // Buscar un vehículo por placa
                    System.out.println("Ingrese la placa del vehículo: ");
                    String placaBusqueda = scanner.nextLine().toUpperCase();
                    
                    if (!vehiculosRegistrados.containsKey(placaBusqueda)) {
                        System.out.println("❌ Vehículo no encontrado.");
                        continue;
                    }
                    
                    Vehiculo vehiculoBuscado = vehiculosRegistrados.get(placaBusqueda);
                    LocalDateTime horaIngresoBusqueda = vehiculoBuscado.getHoraIngreso();
                    long horasEstacionadoBusqueda = Duration.between(horaIngresoBusqueda, LocalDateTime.now()).toHours();
                    int tarifaBusqueda = vehiculoBuscado.calcularTarifa();
                    int totalPagarBusqueda = (int)(tarifaBusqueda * horasEstacionadoBusqueda);
                    System.out.println("Vehículo con placa " + placaBusqueda + " registrado a las " + horaIngresoBusqueda);
                    System.out.println("Tiempo estacionado: " + horasEstacionadoBusqueda + " horas");
                    System.out.println("Total a pagar hasta ahora: $" + totalPagarBusqueda);
                    break;
                    
                case 4:
                    System.out.println("Saliendo del sistema.");
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("❌ Opción no válida.");
            }
        }
    }
}