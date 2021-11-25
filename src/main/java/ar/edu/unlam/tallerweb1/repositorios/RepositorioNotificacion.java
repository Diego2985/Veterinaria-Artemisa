package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Turno;

import java.util.List;

public interface RepositorioNotificacion {
    List<Notificacion> getNotificaciones(Long userId);

    void generaTurnoNotificacion(Turno turno);

    void update(Notificacion notificacion);
}
