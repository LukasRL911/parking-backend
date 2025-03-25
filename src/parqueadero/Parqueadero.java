package com.parking.parqueadero;

import com.parking.Vehiculos.Vehiculo;
import com.parking.database.InventarioVehiculosDatabase;
import com.parking.exceptions.ParqueaderoLlenoException;
import java.util.List;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public abstract class Parqueadero {


    //-------------------------------------------------------------------------------------------------------------------------------------------------------

    public abstract String getMensajeAgregar();
    public abstract String getMensajeRetirar();
    public abstract String getMensajeBuscar();

    //-------------------------------------------------------------------------------------------------------------------------------------------------------

    public void agregarVehiculo(Vehiculo vehiculo) {
        validarDisponibilidad();
        databaseVehiculos().add(vehiculo);
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------

    public void retirarVehiculo(String idtype) {
        boolean removed = databaseVehiculos().removeIf(vehiculo -> vehiculo.getIdtype().equals(idtype));
        if (!removed) {
            throw new RuntimeException("Vehículo no encontrado para retirar");
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------

    public Vehiculo buscarVehiculo(String idtype) {
        return databaseVehiculos().stream()
                .filter(vehiculo -> vehiculo.getIdtype().equalsIgnoreCase(idtype))
                .findFirst()
                .orElse(null);
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------

    private List<Vehiculo> databaseVehiculos() {
        return InventarioVehiculosDatabase.VEHICULOS_INVENTARIO;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------

    public void validarDisponibilidad() {
        long cantidadVehiculosEstacionadosPorTipo = obtenerCantidadDeVehiculosEstacionadosPorTipo(getTipoVehiculo());
        if (cantidadVehiculosEstacionadosPorTipo == obtenerCupoMaximoPermitidoPorTipoVehiculo()) {
            throw new ParqueaderoLlenoException(String.format("Lo sentimos, no hay espacio para vehículos tipo %s, intente más tarde.", getTipoVehiculo()));
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------

    public long obtenerEspaciosDisponibles() {
        long cantidadVehiculosEstacionadosPorTipo = obtenerCantidadDeVehiculosEstacionadosPorTipo(getTipoVehiculo());
        return obtenerCupoMaximoPermitidoPorTipoVehiculo() - cantidadVehiculosEstacionadosPorTipo;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------

    public abstract String getTipoVehiculo();

    //------------------------------------------------------------------------------------------------------------------------------------------------------

    public long obtenerCantidadDeVehiculosEstacionadosPorTipo(String tipoVehiculo) {
        return databaseVehiculos().stream()
                .filter(vehiculo -> vehiculo.getTipoVehiculo().equals(tipoVehiculo))
                .count();
    }


    //------------------------------------------------------------------------------------------------------------------------------------------------------


    public abstract long obtenerCupoMaximoPermitidoPorTipoVehiculo();

    //------------------------------------------------------------------------------------------------------------------------------------------------------

    public long obtenerPrecioParqueadero(String placa) {
        Vehiculo vehiculo = buscarVehiculo(placa);
        return obtenerHorasParqueadero(vehiculo) * obtenerPrecioParqueaderPorTipo();
    }


    //------------------------------------------------------------------------------------------------------------------------------------------------------

    private long obtenerHorasParqueadero(Vehiculo vehiculo) {
        LocalDateTime horaSalida = LocalDateTime.now();
        Duration duracion = Duration.between(vehiculo.getHoraEntrada(), horaSalida);
        long horas = Math.max(duracion.toHours(), 1);
        return horas;
    }


    //------------------------------------------------------------------------------------------------------------------------------------------------------

    public abstract long obtenerPrecioParqueaderPorTipo();

    //------------------------------------------------------------------------------------------------------------------------------------------------------


    public List<Vehiculo> obtenerTop5VehiculosConMasDeuda() {
        return InventarioVehiculosDatabase.VEHICULOS_INVENTARIO.stream()
                .sorted((v1, v2) -> {
                    long deudaV1 = obtenerPrecioParqueadero(v1.getIdtype());
                    long deudaV2 = obtenerPrecioParqueadero(v2.getIdtype());
                    return Long.compare(deudaV2, deudaV1);
                })
                .limit(5)
                .collect(Collectors.toList());
    }


}
