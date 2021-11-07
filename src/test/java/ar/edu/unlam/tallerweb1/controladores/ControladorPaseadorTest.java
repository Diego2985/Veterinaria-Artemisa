package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Paseadores;
import ar.edu.unlam.tallerweb1.converter.Coordenadas;
import ar.edu.unlam.tallerweb1.converter.Ubicacion;
import ar.edu.unlam.tallerweb1.excepciones.DatosCambiadosException;
import ar.edu.unlam.tallerweb1.excepciones.PaseadorConCantMaxDeMascotasException;
import ar.edu.unlam.tallerweb1.modelo.Paseador;
import ar.edu.unlam.tallerweb1.modelo.RegistroPaseo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioPaseador;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControladorPaseadorTest {

    private Double latitud = -34.588902;
    private Double longitud = -58.409851;
    private Coordenadas coordenadas = new Coordenadas(latitud, longitud);
    private Integer distancia = 500;
    private ModelAndView mav;
    private Usuario usuario = new Usuario();
    private ServicioPaseador servicioPaseador = mock(ServicioPaseador.class);
    private ControladorPaseador controladorPaseador = new ControladorPaseador(servicioPaseador);
    private HttpServletRequest request;
    private HttpSession sessionMock = Mockito.mock(HttpSession.class);

    @Before
    public void init() {
        request = mock(HttpServletRequest.class);
        HttpSession sessionMock = mock(HttpSession.class);

        when(request.getSession()).thenReturn(sessionMock);
        when(request.getSession().getAttribute("userId")).thenReturn(1L);
    }

    @Test
    public void verificarQueSeRecibeLaVistaDondeSeCaptaLaUbicacion() {
        mav = whenElUsuarioQuiereElegirLaOpcionParaPasearAlPerro();
        thenTieneQueVerLaVistaInicialDondeSeCaptaLaUbicacion(mav);
    }

    private ModelAndView whenElUsuarioQuiereElegirLaOpcionParaPasearAlPerro() {
        return controladorPaseador.verPaginaDePaseador();
    }

    private void thenTieneQueVerLaVistaInicialDondeSeCaptaLaUbicacion(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("paseador-inicio");
    }

    @Test
    public void recibirLasCoordenadasDeUbicacionDelUsuario() throws IOException {
        givenUnUsuarioYSuUbicacion();
        mav = whenEnviaLasCoordenadas();
        thenDeberiaRecibirlasEnElController(mav);
    }

    private void givenUnUsuarioYSuUbicacion() throws IOException {
        usuario.setEmail("usuario@correo.com");
        usuario.setPassword("12345");

        Ubicacion ubicacion = new Ubicacion();
        Coordenadas coordenadas = new Coordenadas(latitud, longitud);
        ubicacion.setCoordenadas(coordenadas);

        when(servicioPaseador.obtenerDireccionDeUbicacionActual(latitud, longitud)).thenReturn(ubicacion);
    }

    private ModelAndView whenEnviaLasCoordenadas() {
        return controladorPaseador.obtenerPaseadoresCercanosA500mts(latitud, longitud);
    }

    private void thenDeberiaRecibirlasEnElController(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("paseador-mapa");
        Ubicacion ubicacion = (Ubicacion) mav.getModel().get("ubicacion");
        assertThat(ubicacion.getCoordenadas().getLatitud()).isEqualTo(latitud);
        assertThat(ubicacion.getCoordenadas().getLongitud()).isEqualTo(longitud);
    }

    @Test
    public void obtenerIdDePaseador() throws PaseadorConCantMaxDeMascotasException {
        Long id = givenDadoUnPaseador();
        mav = whenContratoAlPaseador(id);
        thenDeboObtenerSuId(mav, id);
    }

    private Long givenDadoUnPaseador() throws PaseadorConCantMaxDeMascotasException {
        Paseador paseador = crearPaseador(1L);
        paseador.setCantidadMaxima(10);
        paseador.setCantidadActual(5);

        when(servicioPaseador.obtenerPaseador(paseador.getId(), true)).thenReturn(paseador);
        return 1L;
    }

    private ModelAndView whenContratoAlPaseador(Long id) {
        return controladorPaseador.contratarAlPaseador(id, latitud, longitud, request);
    }

    private void thenDeboObtenerSuId(ModelAndView mav, Long id) {
        assertThat(mav.getViewName()).isEqualTo("paseador-exitoso");
        assertThat(mav.getModel().get("idPaseador")).isEqualTo(id);
    }

    @Test
    public void obtenerPaseador() throws PaseadorConCantMaxDeMascotasException {
        Paseador paseador = givenUnIdYUnPaseador();
        mav = whenContratoAlPaseador(paseador.getId());
        thenDeboObtenerUnPaseador(mav, paseador);
    }

    private Paseador givenUnIdYUnPaseador() throws PaseadorConCantMaxDeMascotasException {
        Long id = 1L;
        Paseador paseador = crearPaseador(id);
        paseador.setCantidadActual(5);
        paseador.setCantidadMaxima(10);

        when(servicioPaseador.obtenerPaseador(id, true)).thenReturn(paseador);

        return paseador;
    }

    private Paseador crearPaseador(Long id) {
        Paseador paseador = new Paseador();
        paseador.setId(id);
        paseador.setNombre("Matias");
        paseador.setEstrellas(4);
        paseador.setLatitud(latitud);
        paseador.setLongitud(longitud);
        return paseador;
    }


    private void thenDeboObtenerUnPaseador(ModelAndView mav, Paseador paseador) {
        assertThat(mav.getModel().get("paseador")).isEqualTo(paseador);
    }

    @Test
    public void obtenerPaseadoresCercanos() {
        List<Paseador> paseadoresEsperados = givenUnaListaDePaseadores();
        mav = whenSolicitoLosPaseadores();
        thenDeboObtenerLosPaseadoresCercanos(paseadoresEsperados, mav);
    }

    private List<Paseador> givenUnaListaDePaseadores() {
        List<Paseador> paseadores = Paseadores.crearPaseadores();
        paseadores.remove(2);

        when(servicioPaseador.obtenerListaDePaseadoresCercanos(latitud, longitud, distancia)).thenReturn(paseadores);

        return paseadores;
    }

    private ModelAndView whenSolicitoLosPaseadores() {
        return controladorPaseador.obtenerPaseadoresCercanosA500mts(latitud, longitud);
    }

    private void thenDeboObtenerLosPaseadoresCercanos(List<Paseador> paseadoresEsperados, ModelAndView mav) {
        List<Paseador> obtenidos = (List<Paseador>) mav.getModel().get("paseadores");
        assertThat(obtenidos).hasSize(2);
        assertThat(obtenidos).isEqualTo(paseadoresEsperados);
    }

    @Test
    public void siElPaseadorLlegoALaCantidadMaximaNoSeLoDebeContratar() throws PaseadorConCantMaxDeMascotasException {
        Paseador paseador = givenUnPaseadorConCantidadesMaxYActualIguales();
        mav = whenContratoAlPaseador(paseador.getId());
        thenNoPodriaContratarlo(mav);
    }

    private Paseador givenUnPaseadorConCantidadesMaxYActualIguales() throws PaseadorConCantMaxDeMascotasException {
        Paseador paseador = crearPaseador(1L);
        paseador.setCantidadActual(10);
        paseador.setCantidadMaxima(10);

        when(servicioPaseador.obtenerPaseador(1L, true)).thenThrow(PaseadorConCantMaxDeMascotasException.class);

        return paseador;
    }

    private void thenNoPodriaContratarlo(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("paseador-error");
    }

    @Test
    public void seCreaUnRegistroDelPaseo() throws PaseadorConCantMaxDeMascotasException {
        RegistroPaseo registro = givenUnPaseadorUnUsuarioYUnRegistroDePaseo();
        mav = whenContratoAlPaseador(registro.getPaseador().getId());
        thenReciboUnRegistroDelPaseo(mav, registro);
    }

    private RegistroPaseo givenUnPaseadorUnUsuarioYUnRegistroDePaseo() throws PaseadorConCantMaxDeMascotasException {
        RegistroPaseo registro = crearRegistro();

        when(servicioPaseador.obtenerPaseador(registro.getPaseador().getId(), true)).thenReturn(registro.getPaseador());
        when(servicioPaseador.crearRegistroDePaseo(registro.getPaseador(), (Long) request.getSession().getAttribute("userId"))).thenReturn(registro);
        return registro;
    }

    private RegistroPaseo crearRegistro() {
        Paseador paseador = crearPaseador(1L);
        paseador.setId(1L);

        Usuario usuario = new Usuario();
        usuario.setId(1L);

        RegistroPaseo registro = new RegistroPaseo();
        registro.setPaseador(paseador);
        registro.setUsuario(usuario);

        return registro;
    }

    private void thenReciboUnRegistroDelPaseo(ModelAndView mav, RegistroPaseo registro) {
        assertThat(mav.getModel().get("registro")).isEqualTo(registro);
    }

    @Test
    public void verificarQueSeCambioElEstado() throws DatosCambiadosException {
        RegistroPaseo registro = givenUnPaseadorUnUsuarioYOtroRegistro();
        mav = whenQuieroCambiarElEstado(registro);
        thenDEberiaHaberloCambiado(registro);
    }

    private RegistroPaseo givenUnPaseadorUnUsuarioYOtroRegistro() throws DatosCambiadosException {
        RegistroPaseo registro = crearRegistro();

        registro.setEstado(1);
        when(servicioPaseador.actualizarRegistroDePaseo(registro.getId(), registro.getPaseador().getId(), registro.getUsuario().getId(), 1)).thenReturn(registro);

        return registro;
    }

    private ModelAndView whenQuieroCambiarElEstado(RegistroPaseo registro) {
        return controladorPaseador.realizarSeguimientoDePaseo(registro.getId(), registro.getPaseador().getId(), registro.getUsuario().getId());
    }

    private void thenDEberiaHaberloCambiado(RegistroPaseo registro) {
        RegistroPaseo obtenido = (RegistroPaseo) mav.getModel().get("registro");
        assertThat(obtenido.getEstado()).isEqualTo(1);
    }


}
