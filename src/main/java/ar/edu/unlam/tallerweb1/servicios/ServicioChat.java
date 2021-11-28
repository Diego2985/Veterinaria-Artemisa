package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Conversacion;
import ar.edu.unlam.tallerweb1.modelo.OutputMessage;

public interface ServicioChat {

    void guardarMensaje(OutputMessage mensaje);

    Conversacion existeConversacion(Long emisorId, Long receptorId);
}
