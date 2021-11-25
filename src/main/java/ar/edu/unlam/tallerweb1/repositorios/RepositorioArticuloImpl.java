package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Articulo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Repository
@Transactional
@EnableTransactionManagement
public class RepositorioArticuloImpl implements RepositorioArticulo {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioArticuloImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Articulo> getArticulos() {
        return (List<Articulo>) sessionFactory.getCurrentSession()
                .createCriteria(Articulo.class)
                .list();
    }

    @Override
    public List<Articulo> buscarArticuloPorNombre(String busqueda) {
        String busquedaSinEspacios = busqueda.trim();
        String busquedaConLetraCapital = "%" + busquedaSinEspacios.substring(0, 1).toUpperCase() + busquedaSinEspacios.substring(1) + "%";
        String busquedaMinuscula = "%" + busquedaSinEspacios.toLowerCase() + "%";
        return getCurrentSession().createCriteria(Articulo.class).add(Restrictions.disjunction()
                .add(Restrictions.like("descripcion", busquedaMinuscula))
                .add(Restrictions.like("descripcion", busquedaConLetraCapital))
                .add(Restrictions.like("titulo", busquedaMinuscula))
                .add(Restrictions.like("titulo", busquedaConLetraCapital)))
                .list();
    }

    @Override
    public void update(Articulo item) {
        getCurrentSession().save(item);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
