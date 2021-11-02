package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioPaseador;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.*;

public class ControladorPaseadorTest {

    private Double latitud = -34.588902;
    private Double longitud = -58.409851;
    private String coordenadasString =latitud+","+longitud;
    private Coordenadas coordenadasObject;
    private ModelAndView mav;
    private Usuario usuario=new Usuario();
    private ServicioPaseador servicioPaseador=new ServicioPaseadorImpl();
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

        servicioPaseador.obtenerCoordenadasDentroDeUnObjeto(coordenadasString);
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
}
