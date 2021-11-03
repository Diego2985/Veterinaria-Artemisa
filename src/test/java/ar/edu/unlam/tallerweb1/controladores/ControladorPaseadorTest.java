package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Paseador;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioPaseador;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControladorPaseadorTest {

    private Double latitud = -34.588902;
    private Double longitud = -58.409851;
    private Integer distancia=500;
    private ModelAndView mav;
    private Usuario usuario=new Usuario();
    private ServicioPaseador servicioPaseador=mock(ServicioPaseador.class);
    private ControladorPaseador controladorPaseador=new ControladorPaseador(servicioPaseador);

    @Test
    public void verificarQueSeRecibeLaVistaDondeSeCaptaLaUbicacion(){
        mav=whenElUsuarioQuiereElegirLaOpcionParaPasearAlPerro();
        thenTieneQueVerLaVistaInicialDondeSeCaptaLaUbicacion(mav);
    }

    private ModelAndView whenElUsuarioQuiereElegirLaOpcionParaPasearAlPerro() {
        return controladorPaseador.verPaginaDePaseador();
    }

    private void thenTieneQueVerLaVistaInicialDondeSeCaptaLaUbicacion(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("paseador-inicio");
    }

    @Test
    public void recibirLasCoordenadasDeUbicacionDelUsuario(){
        givenUnUsuarioYSuUbicacion();
        mav=whenEnviaLasCoordenadas();
        thenDeberiaRecibirlasEnElController(mav);
    }

    private void givenUnUsuarioYSuUbicacion() {
        usuario.setEmail("usuario@correo.com");
        usuario.setPassword("12345");
    }

    private ModelAndView whenEnviaLasCoordenadas() {
        return controladorPaseador.obtenerPaseadoresCercanosA500mts(latitud, longitud);
    }

    private void thenDeberiaRecibirlasEnElController(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("paseador-mapa");
        assertThat(mav.getModel().get("latitud")).isEqualTo(latitud);
        assertThat(mav.getModel().get("longitud")).isEqualTo(longitud);
    }

    @Test
    public void obtenerIdDePaseador(){
        Long id=givenDadoUnPaseador();
        mav=whenContratoAlPaseador(id);
        thenDeboObtenerSuId(mav, id);
    }

    private Long givenDadoUnPaseador() {
        return 1L;
    }

    private ModelAndView whenContratoAlPaseador(Long id) {
        return controladorPaseador.contratarAlPaseador(id);
    }

    private void thenDeboObtenerSuId(ModelAndView mav, Long id) {
        assertThat(mav.getViewName()).isEqualTo("paseador-exitoso");
        assertThat(mav.getModel().get("idPaseador")).isEqualTo(id);
    }

    @Test
    public void obtenerPaseador(){
        Paseador paseador=givenUnIdYUnPaseador();
        mav=whenContratoAlPaseador(paseador.getId());
        thenDeboObtenerUnPaseador(mav, paseador);
    }

    private Paseador givenUnIdYUnPaseador() {
        Long id=1L;
        Paseador paseador=new Paseador();
        paseador.setId(id);
        paseador.setNombre("Matias");
        paseador.setEstrellas(4);
        paseador.setLatitud(latitud);
        paseador.setLongitud(longitud);

        when(servicioPaseador.obtenerPaseador(id)).thenReturn(paseador);

        return paseador;
    }

    private void thenDeboObtenerUnPaseador(ModelAndView mav, Paseador paseador) {
        assertThat(mav.getModel().get("paseador")).isEqualTo(paseador);
    }

    @Test
    public void obtenerPaseadoresCercanos(){
        List<Paseador> paseadoresEsperados=givenUnaListaDePaseadores();
        mav=whenSolicitoLosPaseadores();
        thenDeboObtenerLosPaseadoresCercanos(paseadoresEsperados, mav);
    }

    private List<Paseador> givenUnaListaDePaseadores() {
        Double latPaseador = -34.58856;
        Double longPaseador = -58.41066;
        Paseador paseador1 = crearPaseador(latPaseador, longPaseador);

        latPaseador = -34.585991;
        longPaseador = -58.407848;
        Paseador paseador2 = crearPaseador(latPaseador, longPaseador);

        List<Paseador> paseadores=new ArrayList<>();
        paseadores.add(paseador1);
        paseadores.add(paseador2);

        when(servicioPaseador.obtenerListaDePaseadoresCercanos(latitud, longitud, distancia)).thenReturn(paseadores);

        return paseadores;
    }

    private ModelAndView whenSolicitoLosPaseadores() {
        return controladorPaseador.obtenerPaseadoresCercanosA500mts(latitud, longitud);
    }

    private void thenDeboObtenerLosPaseadoresCercanos(List<Paseador> paseadoresEsperados, ModelAndView mav) {
        List<Paseador> obtenidos= (List<Paseador>) mav.getModel().get("paseadores");
        assertThat(obtenidos).hasSize(2);
        assertThat(obtenidos).isEqualTo(paseadoresEsperados);
    }

    private Paseador crearPaseador(Double latitud, Double longitud) {
        Paseador paseador=new Paseador();
        paseador.setEstrellas(5);
        paseador.setLatitud(latitud);
        paseador.setLongitud(longitud);
        return paseador;
    }
}
