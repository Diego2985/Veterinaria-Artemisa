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
    private String coordenadasString =latitud+","+longitud;
    private Coordenadas coordenadasObject;
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

        coordenadasObject=new Coordenadas(coordenadasString, latitud, longitud);

        when(servicioPaseador.obtenerCoordenadasDentroDeUnObjeto(coordenadasString)).thenReturn(coordenadasObject);
    }

    private ModelAndView whenEnviaLasCoordenadas() {
        return controladorPaseador.recibirLasCoordenadasDeUbicacion(coordenadasString);
    }

    private void thenDeberiaRecibirlasEnElController(ModelAndView mav) {
        Coordenadas coordenadasObject= (Coordenadas) mav.getModel().get("coordenadas");
        assertThat(mav.getViewName()).isEqualTo("paseador-mapa");
        assertThat(coordenadasObject).isInstanceOf(Coordenadas.class);
        assertThat(coordenadasObject.getLatitud()).isEqualTo(latitud);
        assertThat(coordenadasObject.getLongitud()).isEqualTo(longitud);
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
        Coordenadas coordenadas=servicioPaseador.obtenerCoordenadasDentroDeUnObjeto("-34.58856,-58.41066");
        paseador.setCoordenadas(coordenadas);

        when(servicioPaseador.obtenerPaseador(id)).thenReturn(paseador);

        return paseador;
    }

    private void thenDeboObtenerUnPaseador(ModelAndView mav, Paseador paseador) {
        assertThat(mav.getModel().get("paseador")).isEqualTo(paseador);
    }

    @Test
    public void obtenerUnaListaDePaseadores(){
        List<Paseador> listaObtenida=givenUnaListaDePaseadores();
        mav=whenSolicitoUnaListaDePaseadores();
        thenDeboObtenerLosPaseadoresCercanos(mav, listaObtenida);
    }

    private List<Paseador> givenUnaListaDePaseadores() {
        String coordenadas1="-34.58856,-58.41066";
        String coordenadas2=latitud+","+longitud;
        Paseador paseador1=crearPaseador(1L, coordenadas1);
        Paseador paseador2=crearPaseador(2L, coordenadas2);
        coordenadasObject=servicioPaseador.obtenerCoordenadasDentroDeUnObjeto(coordenadas2);

        List<Paseador> paseadores=new ArrayList<>();
        paseadores.add(paseador1);
        paseadores.add(paseador2);

        when(servicioPaseador.obtenerListaDePaseadoresCercanos(coordenadasObject)).thenReturn(paseadores);

        return paseadores;
    }

    private ModelAndView whenSolicitoUnaListaDePaseadores() {
        return controladorPaseador.recibirLasCoordenadasDeUbicacion(coordenadasString);
    }

    private void thenDeboObtenerLosPaseadoresCercanos(ModelAndView mav, List<Paseador> listaEsperada) {
        List<Paseador> listaObtenida= (List<Paseador>) mav.getModel().get("paseadores");
        assertThat(listaObtenida).isEqualTo(listaEsperada);
    }

    private Paseador crearPaseador(long idPaseador, String coordenadasPaseador) {
        Long id=idPaseador;
        Paseador paseador=new Paseador();
        paseador.setId(id);
        paseador.setNombre("Matias");
        paseador.setEstrellas(4);
        Coordenadas coordenadas=servicioPaseador.obtenerCoordenadasDentroDeUnObjeto(coordenadasPaseador);
        paseador.setCoordenadas(coordenadas);

        return paseador;
    }
}
