package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Conversacion;

import java.util.List;

public interface RepositorioConversaciones {

    List<Conversacion> conversaciones(Long userId);
}
