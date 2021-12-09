package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.RegistroPaseo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPaseador;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class PerfilPaseadorTest {
    private RepositorioPaseador repositorioPaseador = mock(RepositorioPaseador.class);
    private RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
    private ServicioPaseador servicioPaseador = new ServicioPaseadorImpl(repositorioPaseador, repositorioUsuario);

    @Test
    public void ObtenerLosPaseosPendientes() {
        givenUnPaseadorYUnaListaDePaseosPendientes();
        List<RegistroPaseo> paseos = whenSolicitoLosPaseosPendientes();
        thenObtengoLosPaseosPendientesDeEsePaseador(paseos);
    }

    private void givenUnPaseadorYUnaListaDePaseosPendientes() {
        List<RegistroPaseo> paseos = new ArrayList<>();
        RegistroPaseo paseo1 = new RegistroPaseo();
        paseo1.setEstado(0);
        RegistroPaseo paseo2 = new RegistroPaseo();
        paseo2.setEstado(0);
        paseos.add(paseo1);
        paseos.add(paseo2);
        when(repositorioPaseador.obtenerPaseosDeUnPaseador(2L, 0)).thenReturn(paseos);
    }

    private List<RegistroPaseo> whenSolicitoLosPaseosPendientes() {
        return servicioPaseador.obtenerPaseosDeUnPaseador(2L, 0);
    }

    private void thenObtengoLosPaseosPendientesDeEsePaseador(List<RegistroPaseo> paseos) {
        assertThat(paseos).hasSize(2);
        assertThat(paseos.get(0).getEstado()).isEqualTo(0);
        assertThat(paseos.get(1).getEstado()).isEqualTo(0);
    }
}
