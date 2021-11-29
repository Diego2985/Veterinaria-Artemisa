package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Conversacion;
import ar.edu.unlam.tallerweb1.modelo.OutputMessage;

import java.io.Serializable;
import java.util.List;

public interface RepositorioChat {

    void guardarMensaje(OutputMessage mensaje);

    Conversacion existeConversacion(Long emisorId, Long receptorId);

    Serializable crearConversacion(Conversacion conversacion);

    List<OutputMessage> getMensajes(String idConversacion);
}
