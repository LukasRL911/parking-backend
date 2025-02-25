package fabricas;

import vehiculos.Carro;
import vehiculos.Vehiculo;

public class CarroFactory implements FabricaVehiculo {
    @Override
    public Vehiculo crearVehiculo(String placa) {
        return new Carro(placa);
    }
}
