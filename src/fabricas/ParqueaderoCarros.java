package fabricas;

import vehiculos.Carro;
import vehiculos.Vehiculo;

public class ParqueaderoCarros extends Parqueadero {
    @Override
    public Vehiculo crearVehiculo(String placa) {
        return new Carro(placa);
    }
}