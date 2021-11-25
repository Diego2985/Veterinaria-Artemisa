package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Recompensa;
import ar.edu.unlam.tallerweb1.modelo.TipoRecompensa;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioRecompensaTest extends SpringTest {

    @Autowired
    private SessionFactory sessionFactory;
    private RepositorioRecompensa repositorioRecompensa;

    @Before
    public void init() {
        repositorioRecompensa = new RepositorioRecompensaImpl(sessionFactory);
    }

    @Test
    @Rollback
    @Transactional
    public void testElusuarioTieneRecompensaDeTurnoGratis() {
        givenRecompensaDeTurnoGratis();
        List<Recompensa> recompensas = whenTraerRecompensa(TipoRecompensa.TURNO_GRATIS);
        thenHayRecompensaDeTurnoGratis(recompensas);
    }

    @Test
    @Rollback
    @Transactional
    public void testElusuarioNoTieneRecompensa() {
        givenUsuarioSinRecompensas();
        List<Recompensa> recompensas = whenTraerRecompensa(TipoRecompensa.ALIMENTO_GRATIS);
        thenNoHayRecompensa(recompensas);
    }

    @Test
    @Rollback
    @Transactional
    public void testElusuarioTieneRecompensaDeAlimentoGratis() {
        givenRecompensaDeAlimentoGratis();
        List<Recompensa> recompensas = whenTraerRecompensa(TipoRecompensa.ALIMENTO_GRATIS);
        thenHayRecompensaDeAlimentoGratis(recompensas);
    }

    private void thenNoHayRecompensa(List<Recompensa> recompensas) {
        assertThat(recompensas).isEmpty();
    }

    private void givenUsuarioSinRecompensas() {}

    private void givenRecompensaDeAlimentoGratis() {
        Recompensa recompensa = new Recompensa();
        recompensa.setTipo(TipoRecompensa.ALIMENTO_GRATIS);
        recompensa.setUserId(1L);
        session().save(recompensa);
    }

    private void thenHayRecompensaDeAlimentoGratis(List<Recompensa> recompensas) {
        assertThat(recompensas).isNotNull();
        assertThat(recompensas).hasSize(1);
        assertThat(recompensas.get(0).getTipo()).isEqualTo(TipoRecompensa.ALIMENTO_GRATIS);
    }

    private void thenHayRecompensaDeTurnoGratis(List<Recompensa> recompensas) {
        assertThat(recompensas).isNotNull();
        assertThat(recompensas).hasSize(1);
        assertThat(recompensas.get(0).getTipo()).isEqualTo(TipoRecompensa.TURNO_GRATIS);
    }

    private List<Recompensa> whenTraerRecompensa(TipoRecompensa turnoGratis) {
        return repositorioRecompensa.obtenerRecompensas(1L, turnoGratis);
    }

    private void givenRecompensaDeTurnoGratis() {
        Recompensa recompensa = new Recompensa();
        recompensa.setTipo(TipoRecompensa.TURNO_GRATIS);
        recompensa.setUserId(1L);
        session().save(recompensa);
    }
}
