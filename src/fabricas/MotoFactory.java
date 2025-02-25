package fabricas;

import vehiculos.Moto;
import vehiculos.Vehiculo;

public class MotoFactory implements FabricaVehiculo {
    @Override
    public Vehiculo crearVehiculo(String placa) {
        return new Moto(placa);
    }
}
