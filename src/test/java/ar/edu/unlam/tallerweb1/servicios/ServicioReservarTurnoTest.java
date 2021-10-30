package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.excepciones.FechaNoSeleccionada;
import ar.edu.unlam.tallerweb1.excepciones.HoraNoSeleccionada;
import ar.edu.unlam.tallerweb1.excepciones.ServicioNoSeleccionado;
import ar.edu.unlam.tallerweb1.modelo.DatosTurno;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ServicioReservarTurnoTest extends SpringTest {

    @Autowired
    private ServicioReservarTurno servicioReservarTurno;

    private final Date fecha = new Date();
    private DatosTurno datosTurno;
    private Turno turnoRegistrado;
    private Long userId = 1L;

    @Test
    @Transactional
    @Rollback
    public void reservarTurno() {
        givenDatosTurno(fecha, getServicios(), 1500.0, "10:00");

        whenReservarTurno();

        thenElturnoSeReservaConExito();
    }

    @Test(expected = FechaNoSeleccionada.class)
    public void reservarTurnoFallaPorFechaNoSeleccionado() {
        givenDatosTurno(null, getServicios(), 1500.0, "10:00");

        whenReservarTurno();

        thenLaReservaFalla();
    }

    @Test(expected = HoraNoSeleccionada.class)
    public void reservarTurnoFallaPorHoraNoSeleccionada() {
        givenDatosTurno(fecha, null, 1500.0, "");

        whenReservarTurno();

        thenLaReservaFalla();
    }

    @Test(expected = ServicioNoSeleccionado.class)
    public void reservarTurnoFallaPorServicioNoSeleccionado() {
        givenDatosTurno(fecha, null, 1500.0, "10:00");

        whenReservarTurno();

        thenLaReservaFalla();
    }

    private void thenLaReservaFalla() {}

    private void givenDatosTurno(Date fecha, List<String> servicios, Double precio, String horaSeleccionada) {
        servicioReservarTurno.getServicios();
        datosTurno = new DatosTurno()
                .setFecha(fecha)
                .setServiciosSeleccionados(servicios)
                .setPrecio(precio)
                .setHoraSeleccionada(horaSeleccionada);
    }

    private void whenReservarTurno() {
        turnoRegistrado = servicioReservarTurno.reservar(datosTurno, userId);
    }

    private void thenElturnoSeReservaConExito() {
        assertThat(turnoRegistrado).isNotNull();
    }

    private List<String> getServicios() {
        List<String> servicios = new ArrayList<>();
        servicios.add("1");
        servicios.add("2");

        return servicios;
    }
}
