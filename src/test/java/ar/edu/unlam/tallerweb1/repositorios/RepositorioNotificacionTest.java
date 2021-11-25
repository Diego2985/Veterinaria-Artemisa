package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Servicio;
import ar.edu.unlam.tallerweb1.modelo.TipoNotificacion;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioNotificacionTest extends SpringTest {

    @Autowired
    private SessionFactory sessionFactory;
    private RepositorioNotificacion repositorioNotificacion;

    @Before
    public void init() {
        repositorioNotificacion = new RepositorioNotificacionImpl(sessionFactory);
    }

    @Test
    @Rollback
    @Transactional
    public void testMostrarNotificacionDeTurno() {
        givenNotificacionesGuardadas();
        List<Notificacion> notificacions = whenObtenerListadoDeNotificaciones(1L);
        thenElUsuarioTieneNotificaciones(notificacions);
    }

    @Test
    @Rollback
    @Transactional
    public void testMostrarNotificacionDeArticulo() {
        givenNotificacionesDeArticulosGuardadas();
        List<Notificacion> notificacions = whenObtenerListadoDeNotificaciones(1L);
        thenElUsuarioTieneNotificacionesDeArticulo(notificacions);
    }

    @Test
    @Rollback
    @Transactional
    public void testGeneraNotificacionDeTurno() {
        Turno turno = givenUnTurno();
        whenGeneraNotificacoinDeTurno(turno);
        thenElUsuarioTieneUnaNuevaNotificacion();
    }

    @Test
    @Rollback
    @Transactional
    public void testActualizaNotificacionDeTurno() {
        Notificacion notificacion = givenNotificacionDeTurno();
        Notificacion notificacionActualizada = whenActualizaATurnoLeida(notificacion);
        thenLaNotificacionEsActualizada(notificacionActualizada);
    }

    private void thenLaNotificacionEsActualizada(Notificacion notificacionActualizada) {
        assertThat(notificacionActualizada).isNotNull();
        assertThat(notificacionActualizada.getLeida()).isTrue();
    }

    private Notificacion givenNotificacionDeTurno() {
        Notificacion notificacion = new Notificacion();
        notificacion.setId(1L);
        notificacion.setUserId(1L);
        notificacion.setTipo(TipoNotificacion.NUEVO_ARTICULO);
        notificacion.setFecha("2021-11-23");
        notificacion.setLeida(false);
        session().save(notificacion);

        return notificacion;
    }

    private Notificacion whenActualizaATurnoLeida(Notificacion notificacion) {
        notificacion.setLeida(true);
        repositorioNotificacion.update(notificacion);
        return notificacion;
    }

    private void thenElUsuarioTieneUnaNuevaNotificacion() {
        List<Notificacion> notificaciones = repositorioNotificacion.getNotificaciones(1L);
        assertThat(notificaciones).isNotNull();
        assertThat(notificaciones).hasSize(1);
    }

    private void whenGeneraNotificacoinDeTurno(Turno turno) {
        repositorioNotificacion.generaTurnoNotificacion(turno);
    }

    private Turno givenUnTurno() {
        Turno turno = new Turno();
        turno.setId(1L);
        turno.setUserId(1L);
        turno.setFecha(new Date());
        turno.setServicios(getServicios());
        return turno;
    }

    private List<Servicio> getServicios() {
        List<Servicio> servicios = new ArrayList<>();
        servicios.add(new Servicio("Corte de pelo", 300.0));
        servicios.add(new Servicio("Corte de uñas", 100.0));
        servicios.add(new Servicio("Baño", 250.0));
        return servicios;
    }

    private void thenElUsuarioTieneNotificacionesDeArticulo(List<Notificacion> notificacions) {
        assertThat(notificacions).isNotNull();
        assertThat(notificacions).hasSize(1);
    }

    private void givenNotificacionesDeArticulosGuardadas() {
        Notificacion notificacion = new Notificacion();
        notificacion.setId(1L);
        notificacion.setUserId(1L);
        notificacion.setTipo(TipoNotificacion.NUEVO_ARTICULO);
        notificacion.setFecha("2021-11-23");
        notificacion.setLeida(false);
        session().save(notificacion);

        Notificacion notificacion2 = new Notificacion();
        notificacion2.setId(2L);
        notificacion.setUserId(1L);
        notificacion2.setTipo(TipoNotificacion.NUEVO_ARTICULO);
        notificacion2.setFecha("2021-11-23");
        notificacion2.setLeida(false);
        session().save(notificacion2);

        Notificacion notificacion3 = new Notificacion();
        notificacion3.setId(3L);
        notificacion.setUserId(1L);
        notificacion3.setTipo(TipoNotificacion.NUEVO_ARTICULO);
        notificacion3.setFecha("2021-11-23");
        notificacion3.setLeida(true);
        session().save(notificacion3);
    }

    private void thenElUsuarioTieneNotificaciones(List<Notificacion> notificacions) {
        assertThat(notificacions).isNotNull();
        assertThat(notificacions).hasSize(1);
    }

    private List<Notificacion> whenObtenerListadoDeNotificaciones(long userId) {
        return repositorioNotificacion.getNotificaciones(userId);
    }

    private void givenNotificacionesGuardadas() {
        Notificacion notificacion = new Notificacion();
        notificacion.setId(1L);
        notificacion.setUserId(1L);
        notificacion.setTipo(TipoNotificacion.PROXIMO_TURNO);
        notificacion.setFecha("2021-11-23");
        notificacion.setLeida(false);
        session().save(notificacion);

        Notificacion notificacion2 = new Notificacion();
        notificacion2.setId(2L);
        notificacion.setUserId(1L);
        notificacion2.setTipo(TipoNotificacion.PROXIMO_TURNO);
        notificacion2.setFecha("2021-11-23");
        notificacion2.setLeida(false);
        session().save(notificacion2);

        Notificacion notificacion3 = new Notificacion();
        notificacion3.setId(3L);
        notificacion.setUserId(1L);
        notificacion3.setTipo(TipoNotificacion.PROXIMO_TURNO);
        notificacion3.setFecha("2021-11-23");
        notificacion3.setLeida(true);
        session().save(notificacion3);
    }
}
