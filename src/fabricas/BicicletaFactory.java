package fabricas;

import vehiculos.Bicicleta;
import vehiculos.Vehiculo;

public class BicicletaFactory implements FabricaVehiculo {
    @Override
    public Vehiculo crearVehiculo(String identificador) {
        return new Bicicleta();
    }
}
