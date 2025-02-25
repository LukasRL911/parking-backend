package vehiculos;

import java.time.LocalDateTime;

public class Carro implements Vehiculo {
    private final String placa;
    private final LocalDateTime horaEntrada;
    private static final double TARIFA_POR_HORA = 1000;

    public Carro(String placa) {
        this.placa = placa.toUpperCase();
        this.horaEntrada = LocalDateTime.now();
    }

    @Override
    public String getIdentificador() {
        return placa;
    }

    @Override
    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    @Override
    public double calcularTarifa() {
        return TARIFA_POR_HORA;
    }
}
