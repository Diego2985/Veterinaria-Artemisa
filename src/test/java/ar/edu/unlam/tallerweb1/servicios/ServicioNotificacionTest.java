package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.TipoNotificacion;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacion;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioNotificacionTest {

    private ServicioNotificacion servicioNotificacion;
    private RepositorioNotificacion repositorioNotificacion;

    @Before
    public void init() {
        repositorioNotificacion = mock(RepositorioNotificacion.class);
        servicioNotificacion = new ServicioNotificacionImpl(repositorioNotificacion);
    }

    @Test
    @Rollback
    public void testObtenerListadoDeNotificacion() {
        Long userId = givenUserId();
        List<Notificacion> notificaciones = whenUsuarioObtenerListadoDeNotificacion(userId);
        thenUsuarioTieneNotificaciones(notificaciones);
    }

    @Test
    @Rollback
    public void testGenerarNotificacionDeTurno() {
        Turno turno = givenUserConMasDe5Turnos();
        whenGeneraNotificacion(turno);
        thenLaNotificacionEsGuardada();
    }

    @Test
    @Rollback
    public void testActualizarNotificacion() {
        Notificacion notificacion = givenUnaNotificacionLeida();
        whenActualizoNotificacion(notificacion);
        thenLaNotificacionEsLeida();
    }

    private void whenActualizoNotificacion(Notificacion notificacion) {
        Mockito.doNothing().when(repositorioNotificacion).update(notificacion);
        servicioNotificacion.update(notificacion.getId(), 1L);
    }

    private Notificacion givenUnaNotificacionLeida() {
        Notificacion notificacion = new Notificacion();
        notificacion.setId(1L);
        notificacion.setTipo(TipoNotificacion.PROXIMO_TURNO);
        notificacion.setFecha("2021-11-23");
        notificacion.setLeida(false);

        return notificacion;
    }

    private void thenLaNotificacionEsLeida() {}

    private void thenUsuarioTieneNotificaciones(List<Notificacion> notificaciones) {
        assertThat(notificaciones).isNotNull();
        assertThat(notificaciones).isNotEmpty();
    }

    private List<Notificacion> whenUsuarioObtenerListadoDeNotificacion(Long userId) {
        when(repositorioNotificacion.getNotificaciones(userId)).thenReturn(getListadoDeNotificaciones());
        return servicioNotificacion.getNotificaciones(userId);
    }

    private Long givenUserId() {
        return 1L;
    }

    private void thenLaNotificacionEsGuardada() {}

    private void whenGeneraNotificacion(Turno turno) {
        Mockito.doNothing().when(repositorioNotificacion).generaTurnoNotificacion(turno);
        servicioNotificacion.generaTurnoNotificacion(turno);
    }

    private Turno givenUserConMasDe5Turnos() {
        Turno turno = new Turno();
        turno.setId(6L);
        return turno;
    }

    private List<Notificacion> getListadoDeNotificaciones() {
        List<Notificacion> notificaciones = new ArrayList<>();
        Notificacion notificacion = new Notificacion();
        notificacion.setTipo(TipoNotificacion.PROXIMO_TURNO);
        notificacion.setFecha("2021-11-23");
        notificacion.setLeida(false);

        notificaciones.add(notificacion);
        return notificaciones;
    }
}
