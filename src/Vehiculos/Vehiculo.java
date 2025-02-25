package vehiculos;

import java.time.LocalDateTime;

public interface Vehiculo {
    String getIdentificador();
    LocalDateTime getHoraEntrada();
    double calcularTarifa();
}
