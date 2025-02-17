package vehiculos;

import java.time.LocalDateTime;

public abstract class Vehiculo {
    private String placa;
    private LocalDateTime horaIngreso;

    public Vehiculo(String placa) {
        this.placa = placa;
        this.horaIngreso = LocalDateTime.now();
    }

    public String getPlaca() {
        return placa;
    }

    public LocalDateTime getHoraIngreso() {
        return horaIngreso;
    }

    public void registrarEntrada() {
        this.horaIngreso = LocalDateTime.now();  // Sets current time on entry
    }

    // Abstract method to calculate the parking fee
    public abstract int calcularTarifa();
}