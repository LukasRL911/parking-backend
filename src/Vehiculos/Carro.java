package vehiculos;

public class Carro extends Vehiculo {

    public Carro(String placa) {
        super(placa);
    }

    @Override
    public int calcularTarifa() {
        return 1000;  // Tarifa for cars: $1000 per hour
    }
}