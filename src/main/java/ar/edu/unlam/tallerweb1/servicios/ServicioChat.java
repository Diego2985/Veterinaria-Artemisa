package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.OutputMessage;

import java.util.List;

public interface ServicioChat {

    void guardarMensaje(OutputMessage mensaje);

    List<OutputMessage> getMensajes(String idConversacion);
}
