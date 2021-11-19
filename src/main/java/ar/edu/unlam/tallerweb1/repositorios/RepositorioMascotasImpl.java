package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RepositorioMascotasImpl implements RepositorioMascotas {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioMascotasImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Mascota> obtenerPerrosPorIdUsuario(Long idUsuario) {
        return getCurrentSession().createCriteria(Mascota.class).add(Restrictions.and(Restrictions.eq("tipo", "Perro"),
                Restrictions.eq("userId", idUsuario))).list();
    }

    @Override
    public Mascota obtenerMascotaPorId(Long idMascota) {
        return (Mascota) getCurrentSession().createCriteria(Mascota.class).add(Restrictions.eq("id", idMascota)).uniqueResult();
    }

    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
}
