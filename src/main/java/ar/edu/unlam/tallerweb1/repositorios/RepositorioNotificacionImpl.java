package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Turno;
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
public class RepositorioNotificacionImpl implements RepositorioNotificacion {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioNotificacionImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Notificacion> getNotificaciones(Long userId) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Notificacion.class)
                .add(Restrictions.eq("userId", userId))
                .list();
    }

    @Override
    public void generaTurnoNotificacion(Turno turno) {
        Notificacion notificacion = new Notificacion(turno);
        sessionFactory.getCurrentSession().save(notificacion);
    }

    @Override
    public void update(Notificacion notificacion) {
        sessionFactory.getCurrentSession().save(notificacion);
    }
}
