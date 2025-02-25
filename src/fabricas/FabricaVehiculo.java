package fabricas;

import vehiculos.Vehiculo;

public interface FabricaVehiculo {
    Vehiculo crearVehiculo(String identificador);
}
