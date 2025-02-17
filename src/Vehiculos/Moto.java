package vehiculos;

public class Moto extends Vehiculo {

    public Moto(String placa) {
        super(placa);
    }

    @Override
    public int calcularTarifa() {
        return 500;  // Tarifa for motorcycles: $500 per hour
    }
}