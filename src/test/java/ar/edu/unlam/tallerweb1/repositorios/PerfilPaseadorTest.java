package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Paseador;
import ar.edu.unlam.tallerweb1.modelo.RegistroPaseo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class PerfilPaseadorTest extends SpringTest {
    @Autowired
    private RepositorioPaseador repositorioPaseador;

    @Test
    @Transactional
    public void traerListaDePaseosPendientes() {
        givenUnaListaDePaseos();
        List<RegistroPaseo> pendientes = whenSolicitoLosPaseosPendientes(0);
        thenMeRetornaLosPaseosPendientes(pendientes);
    }

    private void givenUnaListaDePaseos() {
        RegistroPaseo paseoPendiente = new RegistroPaseo();
        RegistroPaseo paseoActivo = new RegistroPaseo();
        Paseador paseador = new Paseador();
        session().save(paseador);
        paseoPendiente.setPaseador(paseador);
        paseoActivo.setPaseador(paseador);
        paseoActivo.setEstado(1);
        session().save(paseoPendiente);
        session().save(paseoActivo);
    }

    private List<RegistroPaseo> whenSolicitoLosPaseosPendientes(Integer estado) {
        return repositorioPaseador.obtenerPaseosDeUnPaseador(1L, estado);
    }

    private void thenMeRetornaLosPaseosPendientes(List<RegistroPaseo> pendientes) {
        assertThat(pendientes).hasSize(1);
    }

    @Test
    @Transactional
    public void traerListaDePaseosActivos() {
        givenUnaListaDePaseos();
        List<RegistroPaseo> activos = whenSolicitoLosPaseosPendientes(1);
        thenMeRetornaLosPaseosActivos(activos);
    }

    private void thenMeRetornaLosPaseosActivos(List<RegistroPaseo> activos) {
        assertThat(activos).hasSize(1);
    }
}
