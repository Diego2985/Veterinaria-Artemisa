package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Turno;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@EnableTransactionManagement
public class RepositorioListadoTurnoImpl implements RepositorioListadoTurno {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioListadoTurnoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Turno> getListadoDeTurnos() {
        return (List<Turno>) sessionFactory.getCurrentSession()
                .createCriteria(Turno.class)
                .addOrder(Order.desc("fecha"))
                .list();
    }
}
