package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Conversacion;

import java.util.List;

public interface ServicioConversaciones {

    List<Conversacion> conversaciones(Long userId);
}
