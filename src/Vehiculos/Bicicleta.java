package vehiculos;

import java.time.LocalDateTime;

public class Bicicleta implements Vehiculo {
    private final String codigo;
    private final LocalDateTime horaEntrada;
    private static int contador = 1;
    private static final double TARIFA_POR_HORA = 200;

    public Bicicleta() {
        this.codigo = "BICI" + contador++;
        this.horaEntrada = LocalDateTime.now();
    }

    @Override
    public String getIdentificador() {
        return codigo;
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
