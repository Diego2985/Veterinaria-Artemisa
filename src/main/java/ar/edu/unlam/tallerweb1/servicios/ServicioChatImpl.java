package ar.edu.unlam.tallerweb1.servicios;


import ar.edu.unlam.tallerweb1.modelo.Conversacion;
import ar.edu.unlam.tallerweb1.modelo.OutputMessage;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioChat;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@EnableTransactionManagement
public class ServicioChatImpl implements ServicioChat {

    private final RepositorioChat repositorioChat;
    private final RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioChatImpl(RepositorioChat repositorioChat, RepositorioUsuario repositorioUsuario) {
        this.repositorioChat = repositorioChat;
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public void guardarMensaje(OutputMessage mensaje) {
        Conversacion conversacion = repositorioChat.existeConversacion(
                Long.parseLong(mensaje.getFrom()), mensaje.getUserReceptorId());
        if (conversacion != null) {
            mensaje.setConversacionId(conversacion.getId());
        } else  {
            Conversacion nuevaConversacion = new Conversacion();

            Usuario emisor = repositorioUsuario.buscarUsuarioPorId(Long.parseLong(mensaje.getFrom()));
            nuevaConversacion.setEmisor(emisor);

            Usuario receptor = repositorioUsuario.buscarUsuarioPorId(mensaje.getUserReceptorId());
            nuevaConversacion.setReceptor(receptor);

            Long conversacionId = (Long) repositorioChat.crearConversacion(nuevaConversacion);
            mensaje.setConversacionId(conversacionId);
        }
        repositorioChat.guardarMensaje(mensaje);
    }

    @Override
    public List<OutputMessage> getMensajes(String idConversacion) {
        return repositorioChat.getMensajes(idConversacion);
    }
}
