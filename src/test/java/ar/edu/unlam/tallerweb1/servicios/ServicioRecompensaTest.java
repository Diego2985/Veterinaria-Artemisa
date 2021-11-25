package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Recompensa;
import ar.edu.unlam.tallerweb1.modelo.TipoRecompensa;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioRecompensa;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioRecompensaTest {

    private RepositorioRecompensa repositorioRecompensa;
    private ServicioRecompensa servicioRecompensa;

    @Before
    public void init() {
        repositorioRecompensa = mock(RepositorioRecompensa.class);
        servicioRecompensa = new ServicioRecompensaImpl(repositorioRecompensa);
    }

    @Test
    public void testElusuarioTieneRecompensaDeTurnoGratis() {
        Long userId = givenUsuario();
        List<Recompensa> recompensas = whenTraerRecompensaDeTurnoGratis(userId);
        thenUsuarioTieneRecompensa(recompensas);
    }

    @Test
    public void testElusuarioTieneRecompensaDeAlimentoGratis() {
        Long userId = givenUsuario();
        List<Recompensa> recompensas = whenTraerRecompensaDeAlimentoGratis(userId);
        thenUsuarioTieneRecompensaDeAlimento(recompensas);
    }

    @Test
    public void testElusuarioNoTieneRecompensa() {
        Long userId = givenUsuario();
        List<Recompensa> recompensas = whenTraerRecompensa(userId);
        thenUsuarioNoTieneRecompensa(recompensas);
    }

    private void thenUsuarioNoTieneRecompensa(List<Recompensa> recompensas) {
        assertThat(recompensas).isEmpty();
    }

    private List<Recompensa> whenTraerRecompensa(Long userId) {
        when(repositorioRecompensa.obtenerRecompensas(userId, TipoRecompensa.ALIMENTO_GRATIS)).thenReturn(new ArrayList<>());
        return servicioRecompensa.obtenerRecompensas(userId, TipoRecompensa.ALIMENTO_GRATIS);
    }

    private void thenUsuarioTieneRecompensaDeAlimento(List<Recompensa> recompensas) {
        assertThat(recompensas).isNotNull();
        assertThat(recompensas).hasSize(1);
        assertThat(recompensas.get(0).getTipo()).isEqualTo(TipoRecompensa.ALIMENTO_GRATIS);
    }

    private List<Recompensa> whenTraerRecompensaDeAlimentoGratis(Long userId) {
        when(repositorioRecompensa.obtenerRecompensas(userId, TipoRecompensa.ALIMENTO_GRATIS)).thenReturn(getListadoDeRecompensas(TipoRecompensa.ALIMENTO_GRATIS));
        return servicioRecompensa.obtenerRecompensas(userId, TipoRecompensa.ALIMENTO_GRATIS);
    }

    private void thenUsuarioTieneRecompensa(List<Recompensa> recompensas) {
        assertThat(recompensas).isNotNull();
        assertThat(recompensas).hasSize(1);
        assertThat(recompensas.get(0).getTipo()).isEqualTo(TipoRecompensa.TURNO_GRATIS);
    }

    private List<Recompensa> whenTraerRecompensaDeTurnoGratis(Long userId) {
        when(repositorioRecompensa.obtenerRecompensas(userId, TipoRecompensa.TURNO_GRATIS)).thenReturn(getListadoDeRecompensas(TipoRecompensa.ALIMENTO_GRATIS));
        return servicioRecompensa.obtenerRecompensas(userId, TipoRecompensa.TURNO_GRATIS);
    }

    private List<Recompensa> getListadoDeRecompensas(TipoRecompensa tipoRecompensa) {
        List<Recompensa> recompesas = new ArrayList<>();
        Recompensa recompensa = new Recompensa();
        recompensa.setTipo(tipoRecompensa);
        recompesas.add(recompensa);

        return recompesas;
    }

    private Long givenUsuario() {
        return 1L;
    }
}
