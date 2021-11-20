package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;

import java.util.List;

public interface RepositorioNotificacion {
    List<Notificacion> getNotificaciones(Long userId);
}
