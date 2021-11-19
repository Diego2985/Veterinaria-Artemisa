package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Paseadores;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Paseador;
import ar.edu.unlam.tallerweb1.servicios.ServicioMascotas;
import ar.edu.unlam.tallerweb1.servicios.ServicioPaseador;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ControladorPaseadorMascotaTest {
    private List<Paseador> paseadores = Paseadores.crearPaseadores();
    private Integer distancia = 500;
    private ServicioPaseador servicioPaseador = mock(ServicioPaseador.class);
    private ServicioMascotas servicioMascotas = mock(ServicioMascotas.class);
    private ControladorPaseador controladorPaseador = new ControladorPaseador(servicioPaseador, servicioMascotas);
    private final Double LATITUD = -34.588902;
    private final Double LONGITUD = -58.409851;
    private ModelAndView mav;
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpSession session = mock(HttpSession.class);

    @Before
    public void init() {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("userId")).thenReturn(1L);
    }

    @Test
    public void recibirUnaDistanciaYObtenerPaseadoresDentroDeEsaDistancia() {
        givenUnaDistanciaYUnaListaDePaseadoresCercanos();
        mav = whenFiltroPorDistancia();
        thenObtengoLosPaseadoresCercanos(mav);
    }

    private void givenUnaDistanciaYUnaListaDePaseadoresCercanos() {
        ArrayList<Paseador> paseadoresCercanos = new ArrayList<>();
        paseadoresCercanos.add(paseadores.get(0));
        paseadoresCercanos.add(paseadores.get(1));

        when(servicioPaseador.obtenerListaDePaseadoresCercanos(LATITUD, LONGITUD, distancia)).thenReturn(paseadoresCercanos);
    }

    private ModelAndView whenFiltroPorDistancia() {
        return controladorPaseador.obtenerPaseadoresCercanos(LATITUD, LONGITUD, distancia, request);
    }

    private void thenObtengoLosPaseadoresCercanos(ModelAndView mav) {
        List<Paseador> obtenidos = (List<Paseador>) mav.getModel().get("paseadores");
        assertThat(obtenidos).hasSize(2);
    }

    @Test
    public void verificarQueLaMascotaElegidaSeaUnPerro() {
        givenUnUsuarioConSusMascotas();
        mav = whenSolicitoLasMascotas();
        thenDeberiaObtenerLosQueSonPerros(mav);
    }

    private void givenUnUsuarioConSusMascotas() {
        Mascota perro = new Mascota();
        perro.setId(1L);
        perro.setTipo("Perro");
        perro.setNombre("Firulais");

        Mascota gato = new Mascota();
        gato.setId(2L);
        gato.setTipo("Gato");
        gato.setNombre("Garfield");

        List<Mascota> perros = new ArrayList<>();
        perros.add(perro);

        when(servicioMascotas.obtenerPerrosPorIdUsuario(1L)).thenReturn(perros);
    }

    private ModelAndView whenSolicitoLasMascotas() {
        return controladorPaseador.obtenerPaseadoresCercanos(LATITUD, LONGITUD, distancia, request);
    }

    private void thenDeberiaObtenerLosQueSonPerros(ModelAndView mav) {
        List<Mascota> obtenidas = (List<Mascota>) mav.getModel().get("perros");
        assertThat(obtenidas).hasSize(1);
    }

    @Test
    public void recibirTodosLosPaseosActivosDelUsuario() {

    }
}
