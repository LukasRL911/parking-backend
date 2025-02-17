package fabricas;

import vehiculos.Bicicleta;
import vehiculos.Vehiculo;

public class ParqueaderoBicicletas extends Parqueadero {
    @Override
    public Vehiculo crearVehiculo(String numero) {
        return new Bicicleta(numero);
    }
}