package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class ListadoTurnosTest extends SpringTest {

    @Autowired
    private ControladorListadoTurnos controladorListadoTurnos;

    @Test
    public void testListarTurnosDisponibles() {
        givenInicioDePagina();
        List<Turno> turnos = whenObtenerListadoDeTurnos();
        thenHayTurnosDisponibles(turnos);
    }

    private void thenHayTurnosDisponibles(List<Turno> turnos) {
        assertThat(turnos).isNotNull();
    }

    private List<Turno> whenObtenerListadoDeTurnos() {
       return controladorListadoTurnos.getListadoDeTurnos();
    }

    private void givenInicioDePagina() {

    }
}
