package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.DatosLogin;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioPaseador;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class PerfilPaseadorTest {
    private ModelAndView mav;
    private HttpSession session = mock(HttpSession.class);
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private ServicioLogin servicioLogin = mock(ServicioLogin.class);
    private ServicioPaseador servicioPaseador = mock(ServicioPaseador.class);
    private ControladorPerfilPaseador controladorPaseador = new ControladorPerfilPaseador(servicioPaseador);
    private ControladorLogin controladorLogin = new ControladorLogin(servicioLogin);
    DatosLogin datosLogin = new DatosLogin();

    @Before
    public void init() {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("userId")).thenReturn(2L);
        when(request.getSession().getAttribute("userRol")).thenReturn("2");

        datosLogin.setEmail("paseador@gmail.com");
        datosLogin.setPassword("12345");
    }

    @Test
    public void verificarQueRedirigeAPerfilPaseador() {
        givenUnUsuario();
        mav = whenIniciaSesion();
        thenRedirigeAPerfilDePaseador(mav);
    }

    private void givenUnUsuario() {
        Usuario usuario= new Usuario();
        usuario.setEmail(datosLogin.getEmail());
        usuario.setRol("2");

        when(servicioLogin.consultarUsuario(datosLogin.getEmail(), datosLogin.getPassword())).thenReturn(usuario);
    }

    private ModelAndView whenIniciaSesion() {
        return controladorLogin.validarLogin(datosLogin, request);
    }

    private void thenRedirigeAPerfilDePaseador(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("redirect:paseador/paseos/pendientes");
    }

    @Test
    public void ObtenerLosPaseosPendientes() {
        givenUnaListaDePaseosPendientes();
        mav = whenSolicitoLaListaDePaseosPendientes();
        thenObtengoLaListaDePaseosPendientes(mav);
    }

    private void givenUnaListaDePaseosPendientes() {
        when(servicioPaseador.obtenerPaseosDeUnPaseador(2L, 0)).thenReturn(new ArrayList<>());
    }

    private ModelAndView whenSolicitoLaListaDePaseosPendientes() {
        return controladorPaseador.obtenerPaseosPendientes(request);
    }

    private void thenObtengoLaListaDePaseosPendientes(ModelAndView mav) {
        assertThat(mav.getModel().get("paseos")).isNotNull();
        assertThat(mav.getModel().get("paseos")).isInstanceOf(List.class);
    }
}
