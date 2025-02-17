package fabricas;

import vehiculos.Vehiculo;

public abstract class Parqueadero {
    public abstract Vehiculo crearVehiculo(String identificador);
}