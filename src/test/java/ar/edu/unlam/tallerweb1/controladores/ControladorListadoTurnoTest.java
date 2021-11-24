package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Recompensa;
import ar.edu.unlam.tallerweb1.modelo.TipoRecompensa;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.servicios.ServicioListadoTurno;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioRecompensa;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ControladorListadoTurnoTest  {

    private ControladorListadoTurnos controladorListadoTurnos;
    private ServicioListadoTurno servicioListadoTurno;
    private ServicioNotificacion servicioNotificacion;
    private ServicioRecompensa servicioRecompensa;
    private HttpServletRequest mockHttpServletRequest;
    private ModelAndView modelAndView;
    private Long userId = 1L;

    @Before
    public void init() {
        servicioListadoTurno = mock(ServicioListadoTurno.class);
        servicioNotificacion = mock(ServicioNotificacion.class);
        servicioRecompensa = mock(ServicioRecompensa.class);
        controladorListadoTurnos = new ControladorListadoTurnos(servicioListadoTurno, servicioNotificacion, servicioRecompensa);
        mockHttpServletRequest = mock(HttpServletRequest.class);
    }

    @Test
    public void mostrarListadoDeTurnos() {
        givenUsuario();

        modelAndView = whenGetListadoDeTurnos(userId);

        thenMostrarListadoDeTurnos(modelAndView);
    }

    @Test
    public void mostrarListadoDeNotificaciones() {
        givenUsuario();

        modelAndView = whenGetListadoDeNotificaciones(userId);

        thenMostrarListadoDeNotificaciones(modelAndView);
    }

    @Test
    public void mostrarRecompensaDelUsuario() {
        givenUsuario();

        modelAndView = whenUsuarioTieneRecompensaDeTurnoGratis(userId, TipoRecompensa.TURNO_GRATIS);

        thenMostrarRecompensaDeTurno(modelAndView);
    }

    @Test
    public void elUsuarioNoTieneRecompensa() {
        givenUsuario();

        modelAndView = whenUsuarioNoTieneRecompensaDeTurnoGratis(userId, TipoRecompensa.TURNO_GRATIS);

        thenNoMostrarRecompensaDeTurno(modelAndView);
    }

    private void thenNoMostrarRecompensaDeTurno(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("listado-turnos");
        assertThat(modelAndView.getModel().get("recompensa")).isNull();
    }

    private ModelAndView whenUsuarioNoTieneRecompensaDeTurnoGratis(Long userId, TipoRecompensa tipoRecompensa) {
        when(servicioRecompensa.obtenerRecompensas(userId, tipoRecompensa)).thenReturn(new ArrayList<>());
        return modelAndView = controladorListadoTurnos.irAListadoDeTurnos(mockHttpServletRequest);
    }

    private void thenMostrarRecompensaDeTurno(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("listado-turnos");
        assertThat(modelAndView.getModel().get("recompensa")).isNotNull();
    }

    private ModelAndView whenUsuarioTieneRecompensaDeTurnoGratis(Long userId, TipoRecompensa tipoRecompensa) {
        when(servicioRecompensa.obtenerRecompensas(userId, tipoRecompensa)).thenReturn(getRecompensa());
        return modelAndView = controladorListadoTurnos.irAListadoDeTurnos(mockHttpServletRequest);
    }

    private void givenUsuario() {
        HttpSession sessionMock = mock(HttpSession.class);
        when(mockHttpServletRequest.getSession()).thenReturn(sessionMock);
        when(mockHttpServletRequest.getSession().getAttribute("userId")).thenReturn(1L);
    }

    private void thenMostrarListadoDeNotificaciones(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("listado-turnos");
        assertThat(modelAndView.getModel().get("notificaciones")).isNotNull();
        assertThat((List<Notificacion>)modelAndView.getModel().get("notificaciones")).hasSize(2);
    }

    private ModelAndView whenGetListadoDeNotificaciones(Long userId) {
        when(servicioNotificacion.getNotificaciones(userId)).thenReturn(getListadoDeNotificaciones());
        return modelAndView = controladorListadoTurnos.irAListadoDeTurnos(mockHttpServletRequest);
    }

    private void thenMostrarListadoDeTurnos(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("listado-turnos");
        assertThat(modelAndView.getModel().get("turnos")).isNotNull();
    }

    private ModelAndView whenGetListadoDeTurnos(Long userId) {
        when(servicioListadoTurno.getListadoDeTurnos(userId)).thenReturn(getListadoDeTurnos());
        return modelAndView = controladorListadoTurnos.irAListadoDeTurnos(mockHttpServletRequest);
    }

    private List<Turno> getListadoDeTurnos() {
        List<Turno> turnos = new ArrayList<>();
        turnos.add(new Turno());
        return turnos;
    }

    private List<Notificacion> getListadoDeNotificaciones() {
        List<Notificacion> notificaciones = new ArrayList<>();
        notificaciones.add(new Notificacion());
        notificaciones.add(new Notificacion());
        return notificaciones;
    }

    private List<Recompensa> getRecompensa() {
        List<Recompensa> recompensas = new ArrayList<>();
        recompensas.add(new Recompensa());
        recompensas.add(new Recompensa());
        return recompensas;
    }
}
