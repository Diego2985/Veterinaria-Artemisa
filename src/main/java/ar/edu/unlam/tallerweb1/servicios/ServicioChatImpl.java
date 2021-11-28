package ar.edu.unlam.tallerweb1.servicios;


import ar.edu.unlam.tallerweb1.modelo.Conversacion;
import ar.edu.unlam.tallerweb1.modelo.OutputMessage;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@EnableTransactionManagement
public class ServicioChatImpl implements ServicioChat {

    private final RepositorioChat repositorioChat;

    @Autowired
    public ServicioChatImpl(RepositorioChat repositorioChat) {
        this.repositorioChat = repositorioChat;
    }

    @Override
    public void guardarMensaje(OutputMessage mensaje) {
        Conversacion conversacion = repositorioChat.existeConversacion(
                Long.parseLong(mensaje.getFrom()), mensaje.getUserReceptorId());
        if (conversacion != null) {
            mensaje.setConversacionId(conversacion.getId());
        } else  {
            Conversacion nuevaConversacion = new Conversacion();

            Usuario emisor = new Usuario();
            emisor.setId(Long.parseLong(mensaje.getFrom()));
            nuevaConversacion.setEmisor(emisor);

            Usuario receptor = new Usuario();
            receptor.setId(mensaje.getUserReceptorId());
            nuevaConversacion.setReceptor(receptor);

            Long conversacionId = (Long) repositorioChat.crearConversacion(nuevaConversacion);
            mensaje.setConversacionId(conversacionId);
        }
        repositorioChat.guardarMensaje(mensaje);
    }

    @Override
    public Conversacion existeConversacion(Long emisorId, Long receptorId) {
        return repositorioChat.existeConversacion(emisorId, receptorId);
    }
}
