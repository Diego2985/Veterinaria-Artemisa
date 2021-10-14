package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Articulo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@EnableTransactionManagement
public class RepositorioArticuloImpl implements  RepositorioArticulo {

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
}
