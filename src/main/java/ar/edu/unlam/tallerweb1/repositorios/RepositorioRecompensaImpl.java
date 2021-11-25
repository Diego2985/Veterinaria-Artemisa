package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Recompensa;
import ar.edu.unlam.tallerweb1.modelo.TipoRecompensa;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RepositorioRecompensaImpl implements RepositorioRecompensa {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioRecompensaImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Recompensa> obtenerRecompensas(Long userId, TipoRecompensa tipoRecompensa) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Recompensa.class)
                .add(Restrictions.eq("userId", userId))
                .add(Restrictions.eq("tipo", tipoRecompensa))
                .list();
    }

    @Override
    public void guardarRecompensa(Recompensa recompensa) {
        sessionFactory.getCurrentSession().save(recompensa);
    }
}
