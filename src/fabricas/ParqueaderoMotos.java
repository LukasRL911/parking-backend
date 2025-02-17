package fabricas;

import vehiculos.Moto;
import vehiculos.Vehiculo;

public class ParqueaderoMotos extends Parqueadero {
    @Override
    public Vehiculo crearVehiculo(String placa) {
        return new Moto(placa);
    }
}