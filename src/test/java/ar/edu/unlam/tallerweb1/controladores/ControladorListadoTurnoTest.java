package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ControladorListadoTurnoTest extends SpringTest  {

    @Autowired
    private ControladorListadoTurnos controladorListadoTurnos;
    private Long userId = 1L;

    @Test
    public void getListadoDeTurnos() {
        givenUsuario();

        List<Turno> turnos =  whenGetListadoDeTurnos(userId);

        thenMostrarListado(turnos);
    }

    private void givenUsuario() {}

    private void thenMostrarListado(List<Turno> turnos) {
        assertThat(turnos).isNotNull();
    }

    private List<Turno> whenGetListadoDeTurnos(Long userId) {
        return controladorListadoTurnos.getListadoDeTurnos(userId);
    }
}
