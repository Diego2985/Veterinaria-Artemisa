package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Articulo;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioArticulo;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorArticuloTest {

    private ControladorArticulo controladorArticulo;
    private ServicioArticulo servicioArticulo;
    private ServicioNotificacion servicioNotificacion;
    private HttpServletRequest mockHttpServletRequest;
    private ModelAndView modelAndView;
    private Long userId = 1L;
    private Long notificacionId = 1L;

    @Before
    public void init() {
        servicioNotificacion = mock(ServicioNotificacion.class);
        servicioArticulo = mock(ServicioArticulo.class);
        controladorArticulo = new ControladorArticulo(servicioArticulo, servicioNotificacion);
        mockHttpServletRequest = mock(HttpServletRequest.class);
        setSesion();
    }

    @Test
    public void testSeteaNotificacionLeida() {
        Notificacion notificacion = givenNotificacionMostrada();
        modelAndView = whenTocaEnLaNotificacionLaSeteaComoLeida(notificacion.getId());
        thenLaNotificacionQuedaLeidaYNoSeMuestraMas();
    }

    @Test
    public void testSeteaArticuloMostrado() {
        Articulo articulo = givenArticuloMostrado();
        modelAndView = whenCierroCartelDeArticulo(articulo.getId());
        thenElArticuloNoSeMuestraMas();
    }

    private void thenElArticuloNoSeMuestraMas() {
        assertThat((List<Articulo>)modelAndView.getModel().get("articulos")).isEmpty();
    }

    private ModelAndView whenCierroCartelDeArticulo(Long articuloId) {
        Mockito.doNothing().when(servicioArticulo).update(articuloId);
        when(servicioArticulo.getArticulos()).thenReturn(getListadoDeArticulos());
        when(servicioNotificacion.getNotificaciones(userId)).thenReturn(getListadoDeNotificaciones());
        return controladorArticulo.setearArticuloMostrado(articuloId, mockHttpServletRequest);
    }

    private void thenLaNotificacionQuedaLeidaYNoSeMuestraMas() {
        assertThat((List<Notificacion>)modelAndView.getModel().get("notificaciones")).isEmpty();
    }

    private ModelAndView whenTocaEnLaNotificacionLaSeteaComoLeida(Long notificacionId) {
        Mockito.doNothing().when(servicioNotificacion).update(notificacionId, userId);
        when(servicioArticulo.getArticulos()).thenReturn(getListadoDeArticulos());
        when(servicioNotificacion.getNotificaciones(userId)).thenReturn(getListadoDeNotificaciones());
        return controladorArticulo.setearNotificacionLeida(notificacionId, mockHttpServletRequest);
    }

    private void setSesion() {
        HttpSession sessionMock = mock(HttpSession.class);
        when(mockHttpServletRequest.getSession()).thenReturn(sessionMock);
        when(mockHttpServletRequest.getSession().getAttribute("userId")).thenReturn(userId);
    }

    private Articulo givenArticuloMostrado() {
        Articulo articulo = new Articulo();
        articulo.setId(1L);
        articulo.setMostrado(true);
        return articulo;
    }

    private Notificacion givenNotificacionMostrada() {
        return new Notificacion().setId(notificacionId);
    }

    private List<Articulo> getListadoDeArticulos() {
        List<Articulo> articulos = new ArrayList<>();
        articulos.add(new Articulo());
        articulos.add(new Articulo());
        return articulos;
    }

    private List<Notificacion> getListadoDeNotificaciones() {
        List<Notificacion> notificaciones = new ArrayList<>();
        return notificaciones;
    }
}
