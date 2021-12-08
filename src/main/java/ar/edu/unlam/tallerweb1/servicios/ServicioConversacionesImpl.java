package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Conversacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioConversaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServicioConversacionesImpl implements ServicioConversaciones {

    private final RepositorioConversaciones repositorioConversaciones;

    @Autowired
    public ServicioConversacionesImpl(RepositorioConversaciones repositorioConversaciones) {
        this.repositorioConversaciones = repositorioConversaciones;
    }

    @Override
    public List<Conversacion> conversaciones(Long userId) {
        return repositorioConversaciones.conversaciones(userId);
    }
}
