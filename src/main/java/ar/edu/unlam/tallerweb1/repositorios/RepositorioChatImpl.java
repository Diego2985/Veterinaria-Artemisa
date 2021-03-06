package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Conversacion;
import ar.edu.unlam.tallerweb1.modelo.OutputMessage;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository
@Transactional
@EnableTransactionManagement
public class RepositorioChatImpl implements RepositorioChat {

    private final SessionFactory sessionFactory;
    private final RepositorioUsuario repositorioUsuario;

    @Autowired
    public RepositorioChatImpl(SessionFactory sessionFactory, RepositorioUsuario repositorioUsuario) {
        this.sessionFactory = sessionFactory;
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public void guardarMensaje(OutputMessage mensaje) {
        getCurrentSession().save(mensaje);
    }

    @Override
    public Conversacion existeConversacion(Long emisorId, Long receptorId) {
        Criteria criteria = getCurrentSession()
                .createCriteria(Conversacion.class);

        Usuario emisor = repositorioUsuario.buscarUsuarioPorId(emisorId);

        Usuario receptor = repositorioUsuario.buscarUsuarioPorId(receptorId);

        Criterion rest1 = Restrictions.and(Restrictions.eq("emisor", emisor),
                Restrictions.eq("receptor", receptor));

        Criterion rest2 = Restrictions.and(Restrictions.eq("emisor", receptor),
                Restrictions.eq("receptor", emisor));

        return (Conversacion) criteria.add(Restrictions.or(rest1, rest2)).uniqueResult();
    }

    @Override
    public Serializable crearConversacion(Conversacion conversacion) {
        return getCurrentSession().save(conversacion);
    }

    @Override
    public List<OutputMessage> getMensajes(String idConversacion) {
        return getCurrentSession()
                .createCriteria(OutputMessage.class)
                .add(Restrictions.eq("conversacionId", Long.parseLong(idConversacion)))
                .list();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
