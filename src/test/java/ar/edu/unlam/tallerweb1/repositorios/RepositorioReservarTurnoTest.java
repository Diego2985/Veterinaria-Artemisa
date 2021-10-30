package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.DatosTurno;
import ar.edu.unlam.tallerweb1.modelo.Servicio;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RepositorioReservarTurnoTest extends SpringTest {

    @Autowired
    private RepositorioReservarTurno repositorioReservarTurno;

    private final Date fecha = new Date();
    private Turno turno;
    private Long id;
    private Long userId = 1L;

    @Test
    @Transactional
    @Rollback
    public void reservarTurno() {
        givenTurnoCreado(fecha, getServicios(), 1500.0);

        whenReservarTurno(turno);

        thenElTurnoSeGuardaConExito(id);
    }

    @Test
    @Transactional
    @Rollback
    public void turnoNoDisponible() {
        givenTurnoYaExistente(fecha, "10:00", getServicios(), 1500.0);

        whenSeReservaTurnoMismaFechaYHora(fecha, "10:00");

        thenElTurnoNoSeReserva();
    }

    private void thenElTurnoNoSeReserva() {
        assertThat(turno).isNotNull();
    }

    private void whenSeReservaTurnoMismaFechaYHora(Date fecha, String hora) {
        turno = repositorioReservarTurno.consultarTurno(fecha, hora);
    }

    private void givenTurnoYaExistente(Date fecha, String hora, List<Servicio> servicios, Double precio) {
        DatosTurno datosTurno = new DatosTurno(fecha, precio, servicios);
        datosTurno.setHoraSeleccionada(hora);
        repositorioReservarTurno.reservar(new Turno(datosTurno, userId));
    }

    private void givenTurnoCreado(Date fecha, List<Servicio> servicios, Double precio) {
        DatosTurno datosTurno = new DatosTurno(fecha, precio, servicios);
        turno = new Turno(datosTurno, userId);
    }

    private void whenReservarTurno(Turno turno) {
        id = (Long) repositorioReservarTurno.reservar(turno);
    }

    private void thenElTurnoSeGuardaConExito(Long id) {
        Turno turno = session().get(Turno.class, id);

        assertThat(turno).isNotNull();
        assertThat(turno.getId()).isEqualTo(id);
    }

    private List<Servicio> getServicios() {
        List<Servicio> servicios = new ArrayList<>();
        servicios.add(new Servicio("Corte de pelo", 300.0));
        servicios.add(new Servicio("Corte de uñas", 100.0));
        servicios.add(new Servicio("Baño", 250.0));
        return servicios;
    }
}
