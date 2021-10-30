package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.DatosTurno;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorReservarTurnoTest extends SpringTest {

    @Autowired
    private ControladorReservarTurno controladorReservarTurno;

    private final Date fecha = new Date();
    private ModelAndView modelAndView;
    private HttpServletRequest mockHttpServletRequest = mock(HttpServletRequest.class);


    @Before
    public void init() {
        mockHttpServletRequest = Mockito.mock(HttpServletRequest.class);
        HttpSession sessionMock = Mockito.mock(HttpSession.class);

        when(mockHttpServletRequest.getSession()).thenReturn(sessionMock);
        when(mockHttpServletRequest.getSession().getAttribute("userId")).thenReturn(1L);
    }

    @Test
    @Transactional
    @Rollback
    public void reservarTurnoExitoso() {
        givenDatosInicialesCargados();

        whenReservarTurno(fecha, getServicios(), 1500.0, "09:00");

        thenElTurnoSeSeReservaConExito();
    }

    @Test
    public void fechaNoSeleccionado() {
        givenDatosInicialesCargados();

        whenReservarTurno(null, getServicios(), 1500.0, "09:00");

        thenFallaReservarTurno("Fecha no seleccionada");
    }

    @Test
    public void horaNoSeleccionada() {
        givenDatosInicialesCargados();

        whenReservarTurno(null, getServicios(), 1500.0, "");

        thenFallaReservarTurno("Fecha no seleccionada");
    }

    @Test
    public void servicioNoSeleccionado() {
        givenDatosInicialesCargados();

        whenReservarTurno(fecha, null, 1500.0, "09:00");

        thenFallaReservarTurno("Servicio no seleccionado");
    }

    private void givenDatosInicialesCargados() {
        ModelMap modelMap = new ModelMap();
        controladorReservarTurno.getDatosIniciales(modelMap);
    }

    private void whenReservarTurno(Date fecha, List<String> servicios, Double precio, String horaSeleccionada) {
        DatosTurno datosTurno = new DatosTurno()
                .setFecha(fecha)
                .setServiciosSeleccionados(servicios)
                .setPrecio(precio)
                .setHoraSeleccionada(horaSeleccionada);

        modelAndView = controladorReservarTurno.reservar(datosTurno, mockHttpServletRequest);
    }

    private void thenElTurnoSeSeReservaConExito() {
        assertThat(modelAndView.getModel().get("reservado")).isEqualTo(Boolean.TRUE);
        assertThat(modelAndView.getViewName()).isEqualTo("redirect:/reserva-exitosa");
    }

    private void thenFallaReservarTurno(String mensaje) {
        Assertions.assertThat(modelAndView.getModel().get("error")).isEqualTo(mensaje);
        Assertions.assertThat(modelAndView.getViewName()).isEqualTo("reservar-turno");
    }

    private List<String> getServicios() {
        List<String> servicios = new ArrayList<>();
        servicios.add("1");
        servicios.add("2");

        return servicios;
    }
}
