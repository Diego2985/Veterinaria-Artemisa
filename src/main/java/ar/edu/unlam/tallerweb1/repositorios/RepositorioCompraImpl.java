package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Articulo;
import ar.edu.unlam.tallerweb1.modelo.Compra;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Repository @Transactional @EnableTransactionManagement
public class RepositorioCompraImpl implements RepositorioCompra{


    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCompraImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Compra buscarCompraPorId(Long id) {
        return (Compra) sessionFactory.getCurrentSession().createCriteria(Compra.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public Articulo buscarArticuloPorId(Long id) {
        return (Articulo) sessionFactory.getCurrentSession().createCriteria(Articulo.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
    }
}
