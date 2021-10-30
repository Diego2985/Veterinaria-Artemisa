package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.DatosTurno;
import ar.edu.unlam.tallerweb1.modelo.Servicio;
import ar.edu.unlam.tallerweb1.modelo.Turno;

import java.util.List;

public interface ServicioReservarTurno {
    Turno reservar(DatosTurno datosTurno, Long userId);

    List<Servicio> getServicios();

    List<String> getHorasDisponibles();

    String getFechaDesde();

    String getFechaHasta();
}
