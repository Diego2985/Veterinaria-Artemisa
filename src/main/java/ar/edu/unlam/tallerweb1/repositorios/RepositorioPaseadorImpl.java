package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Paseador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RepositorioPaseadorImpl implements RepositorioPaseador {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioPaseadorImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Paseador> obtenerPaseadores() {
        return null;
    }

    @Override
    public List<Paseador> obtenerPaseadoresCercanos(Double latitud, Double longitud, Double diferenciaLatitud, Double diferenciaLongitud) {
        return getCurrentSession().createCriteria(Paseador.class).add(Restrictions.and(
                        Restrictions.between("latitud", latitud - diferenciaLatitud, latitud + diferenciaLatitud),
                        Restrictions.between("longitud", longitud - diferenciaLongitud, longitud + diferenciaLongitud)))
                .list();
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
