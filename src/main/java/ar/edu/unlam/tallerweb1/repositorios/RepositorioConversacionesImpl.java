package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Conversacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RepositorioConversacionesImpl implements RepositorioConversaciones {

    private final SessionFactory sessionFactory;
    private final RepositorioUsuario repositorioUsuario;

    @Autowired
    public RepositorioConversacionesImpl(SessionFactory sessionFactory, RepositorioUsuario repositorioUsuario) {
        this.sessionFactory = sessionFactory;
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public List<Conversacion> conversaciones(Long userId) {
        Usuario emisor = repositorioUsuario.buscarUsuarioPorId(userId);

        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Conversacion.class);

        Criterion rest1 = Restrictions.and(Restrictions.eq("emisor", emisor));

        Criterion rest2 = Restrictions.and(Restrictions.eq("receptor", emisor));

        return (List<Conversacion>) criteria.add(Restrictions.or(rest1, rest2)).list();
    }
}
