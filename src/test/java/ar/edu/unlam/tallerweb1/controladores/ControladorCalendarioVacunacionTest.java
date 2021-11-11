package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Vacuna;
import ar.edu.unlam.tallerweb1.servicios.ServicioCalendarioVacunacion;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorCalendarioVacunacionTest {

    private ServicioCalendarioVacunacion servicioCalendarioVacunacion;
    private ControladorCalendarioVacunacion controladorCalendarioVacunacion;
    private HttpServletRequest mockHttpServletRequest;
    private ModelAndView modelAndView;

    @Before
    public void init() {
        servicioCalendarioVacunacion = mock(ServicioCalendarioVacunacion.class);
        controladorCalendarioVacunacion = new ControladorCalendarioVacunacion(servicioCalendarioVacunacion);
        mockHttpServletRequest = mock(HttpServletRequest.class);
        HttpSession sessionMock = mock(HttpSession.class);

        when(mockHttpServletRequest.getSession()).thenReturn(sessionMock);
        when(mockHttpServletRequest.getSession().getAttribute("userId")).thenReturn(1L);
    }

    @Test
    public void testCargaCalendarioDeVacunasParaUnUsuarioDado() {
        givenDatosIniciales();
        whenTraerVacunas();
        thenCargarCalendario();
    }

    private void thenCargarCalendario() {
        assertThat(modelAndView).isNotNull();
        assertThat(modelAndView.getViewName()).isEqualTo("calendario-vacunacion");
        assertThat(modelAndView.getModel().get("vacunas")).isNotNull();
    }

    private void whenTraerVacunas() {
        when(servicioCalendarioVacunacion.getVacunas(1L)).thenReturn(getListaDeVacuans());
        modelAndView = controladorCalendarioVacunacion.irACalendarioVacunacion(mockHttpServletRequest);
    }

    private List<Vacuna> getListaDeVacuans() {
        List<Vacuna> vacunas = new ArrayList<>();
        vacunas.add(new Vacuna());
        vacunas.add(new Vacuna());
        vacunas.add(new Vacuna());

        return vacunas;
    }

    private void givenDatosIniciales() {}
}
