package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Servicio;
import ar.edu.unlam.tallerweb1.modelo.Turno;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RepositorioReservarTurno {

    Serializable reservar(Turno turno);

    List<Servicio> getServicios();

    List<String> getHorasDisponibles();

    Optional<Servicio> findServicioById(String idsServicio);

    Turno consultarTurno(Date fecha, String hora);
}
