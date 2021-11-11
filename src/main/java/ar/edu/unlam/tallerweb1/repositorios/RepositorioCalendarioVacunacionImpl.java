package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Vacuna;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@EnableTransactionManagement
public class RepositorioCalendarioVacunacionImpl implements RepositorioCalendarioVacunacion {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCalendarioVacunacionImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Vacuna> getVacunas(String tipo) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Vacuna.class)
                .add(Restrictions.eq("tipo", tipo))
                .list();
    }

    @Override
    public List<Mascota> getMascotas(Long userId) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Mascota.class)
                .add(Restrictions.eq("userId", userId))
                .list();
    }
}
