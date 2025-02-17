package vehiculos;

public class Bicicleta extends Vehiculo {

    public Bicicleta(String placa) {
        super(placa);
    }

    @Override
    public int calcularTarifa() {
        return 200;  
    }
}