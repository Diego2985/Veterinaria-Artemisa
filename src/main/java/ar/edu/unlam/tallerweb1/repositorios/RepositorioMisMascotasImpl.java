package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RepositorioMisMascotasImpl implements RepositorioMisMascotas {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioMisMascotasImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Mascota> obtenerMascotas(Long userId) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Mascota.class)
                .add(Restrictions.eq("userId", userId))
                .list();
    }
}
