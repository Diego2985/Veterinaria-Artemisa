package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.RegistroPaseo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPaseador;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ServicioPaseadorMascotasTest {
    private RepositorioPaseador repositorioPaseador = mock(RepositorioPaseador.class);
    private RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
    private ServicioPaseador servicioPaseador = new ServicioPaseadorImpl(repositorioPaseador, repositorioUsuario);

    @Test
    public void separarLosPaseosPorEstado() {
        givenUnaListaDePaseos();
        Map<String, List<RegistroPaseo>> obtenidos = whenSolicitoLosPaseos();
        thenDeberianEstarSeparadosPorEstado(obtenidos);
    }

    private void givenUnaListaDePaseos() {
        RegistroPaseo enProceso = new RegistroPaseo();
        enProceso.setEstado(0);

        RegistroPaseo activo = new RegistroPaseo();
        activo.setEstado(1);

        RegistroPaseo finalizado = new RegistroPaseo();
        finalizado.setEstado(2);

        List<RegistroPaseo> paseos = new ArrayList<>();
        paseos.add(enProceso);
        paseos.add(activo);
        paseos.add(finalizado);

        when(repositorioPaseador.obtenerTodosLosPaseosDeUnUsuario(1L)).thenReturn(paseos);
    }

    private Map<String, List<RegistroPaseo>> whenSolicitoLosPaseos() {
        return servicioPaseador.obtenerTodosLosRegistrosDePaseoDelUsuario(1L);
    }

    private void thenDeberianEstarSeparadosPorEstado(Map<String, List<RegistroPaseo>> obtenidos) {
        assertThat(obtenidos.get("proceso")).hasSize(1);
        assertThat(obtenidos.get("activos")).hasSize(1);
        assertThat(obtenidos.get("finalizados")).hasSize(1);
    }

    @Test
    public void seContrataAlPaseadorYCambiaElEstadoDePaseoDelPerro() {
        Mascota mascota = givenUnaMascotaDeTipoPerro();
        whenSeContrataUnPaseo(mascota);
        thenCambiaElEstadoDePaseoDeLaMascota(mascota);
    }

    private Mascota givenUnaMascotaDeTipoPerro() {
        Mascota mascota = new Mascota();
        mascota.setTipo("Perro");
        doNothing().when(repositorioPaseador).cambiarEstadoDePaseoDePerro(mascota);
        return mascota;
    }

    private void whenSeContrataUnPaseo(Mascota mascota) {
        servicioPaseador.cambiarEstadoDePaseoDeMascota(mascota);
    }

    private void thenCambiaElEstadoDePaseoDeLaMascota(Mascota mascota) {
        assertThat(mascota.getPaseoActivo()).isTrue();
    }
}
