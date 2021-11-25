package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Turno;

import java.util.List;

public interface ServicioNotificacion {
    List<Notificacion> getNotificaciones(Long userId);

    void generaTurnoNotificacion(Turno turno);

    void update(Long idNotificacion, Long userId);
}
