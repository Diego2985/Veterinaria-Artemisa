package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;

import java.util.List;

public interface ServicioNotificacion {
    List<Notificacion> getNotificaciones(Long userId);
}
