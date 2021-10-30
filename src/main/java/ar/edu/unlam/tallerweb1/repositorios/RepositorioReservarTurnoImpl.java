package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Servicio;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@EnableTransactionManagement
public class RepositorioReservarTurnoImpl implements RepositorioReservarTurno {

    private final SessionFactory sessionFactory;
    private List<Servicio> servicios;

    @Autowired
    public RepositorioReservarTurnoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Serializable reservar(Turno turno) {
        Session session = sessionFactory.getCurrentSession();
        return session.save(turno);
    }

    @Override
    public List<Servicio> getServicios() {
        servicios = sessionFactory.getCurrentSession()
                .createCriteria(Servicio.class)
                .list();

        return servicios;
    }

    @Override
    public List<String> getHorasDisponibles() {
        List<String> horas = new ArrayList<>();
        horas.add("09:00");
        horas.add("10:00");
        horas.add("11:00");
        horas.add("12:00");
        horas.add("13:00");
        horas.add("14:00");
        horas.add("15:00");
        horas.add("16:00");
        horas.add("17:00");
        horas.add("18:00");
        return horas;
    }

    @Override
    public Optional<Servicio> findServicioById(String idsServicio) {
        return servicios.stream()
                .filter(item -> item.getId() == Long.parseLong(idsServicio))
                .findFirst();
    }

    @Override
    public Turno consultarTurno(Date fecha, String hora) {
        return (Turno) sessionFactory.getCurrentSession()
                .createCriteria(Turno.class)
                .add(Restrictions.eq("fecha", fecha))
                .add(Restrictions.eq("hora", hora))
                .uniqueResult();
    }
}
