package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Paseador;
import ar.edu.unlam.tallerweb1.modelo.RegistroPaseo;
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

    @Override
    public Paseador obtenerUnPaseador(Long id) {
        return (Paseador) getCurrentSession().createCriteria(Paseador.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void crearRegistroDePaseo(RegistroPaseo registro) {
        getCurrentSession().save(registro);
    }

    @Override
    public void actualizarEstadoDePaseo(RegistroPaseo registro) {
        getCurrentSession().update(registro);
    }

    @Override
    public RegistroPaseo buscarUnRegistroDePaseo(Long idRegistro) {
        return (RegistroPaseo) getCurrentSession().createCriteria(RegistroPaseo.class).add(Restrictions.eq("id", idRegistro)).uniqueResult();
    }

    @Override
    public RegistroPaseo buscarPaseoEnProcesoOActivoDeUnUsuario(Long userId) {
        return (RegistroPaseo) getCurrentSession().createCriteria(RegistroPaseo.class).add(Restrictions.and(Restrictions.eq("usuario.id", userId), Restrictions.or(Restrictions.eq("estado", 0), Restrictions.eq("estado", 1)))).uniqueResult();
    }

    @Override
    public List<RegistroPaseo> obtenerTodosLosPaseosDeUnUsuario(Long userId) {
        return getCurrentSession().createCriteria(RegistroPaseo.class).add(Restrictions.eq("usuario.id", userId)).list();
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
