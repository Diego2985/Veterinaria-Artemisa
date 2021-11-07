package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Vacuna;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RepositorioCalendarioVacunacionTest  extends SpringTest {

    @Autowired
    RepositorioCalendarioVacunacion repositorioCalendarioVacunacion;

    @Test
    @Transactional
    @Rollback
    public void testTraerMascotaPerro() {
        givenUsuarioConMascotaPerro();
        List<Mascota> mascotas = whenTraerMascotaPerro();
        thenUsuarioTieneSoloMascotaPerro(mascotas);
    }

    @Test
    @Transactional
    @Rollback
    public void testTraerVacunaDePerro() {
        givenMascotaConVacunasDePerro();
        List<Vacuna> vacunas = whenTraerVacunasDePerro();
        thenMascotaTieneVacunasDePerro(vacunas);
    }

    @Test
    @Transactional
    @Rollback
    public void testUsuarioTieneSoloMascotaGato() {
        givenUsuarioConMascotaGato();
        List<Mascota> mascotas = whenTraerMascotaGato();
        thenUsuarioTieneSoloMascotaGato(mascotas);
    }

    @Test
    @Transactional
    @Rollback
    public void testTraerVacunasDeGato() {
        givenMascotaConVacunasDeGato();
        List<Vacuna> vacunas = whenTraerVacunasDeGato();
        thenMascotaTieneVacunasDeGato(vacunas);
    }

    private void thenMascotaTieneVacunasDeGato(List<Vacuna> vacunas) {
        assertThat(vacunas).isNotNull();
        assertThat(vacunas).hasSize(2);
        assertThat(vacunas.get(0).getTipo()).isEqualTo("gato");
    }

    private List<Vacuna> whenTraerVacunasDeGato() {
        return repositorioCalendarioVacunacion.getVacunas("gato");
    }

    private void givenMascotaConVacunasDeGato() {
        Vacuna vacuna1 = new Vacuna()
                .setTipo("gato")
                .setMeses(2)
                .setTitulo("Doble")
                .setId(1L);

        Vacuna vacuna2 = new Vacuna()
                .setTipo("gato")
                .setMeses(3)
                .setTitulo("Triple Virica")
                .setId(1L);

        session().save(vacuna1);
        session().save(vacuna2);
    }

    private void thenUsuarioTieneSoloMascotaGato(List<Mascota> mascotas) {
        assertThat(mascotas).isNotNull();
        assertThat(mascotas).hasSize(1);
        assertThat(mascotas.get(0).getTipo()).isEqualTo("gato");
    }

    private List<Mascota> whenTraerMascotaGato() {
        return repositorioCalendarioVacunacion.getMascotas(1L);
    }

    private void givenUsuarioConMascotaGato() {
        Mascota mascota = new Mascota()
                .setId(1L)
                .setFechaNacimiento(Date.from(Instant.parse("2020-12-03T10:15:30.00Z")))
                .setNombre("Pipi")
                .setTipo("gato")
                .setUserId(1L);
        session().save(mascota);
    }

    private void thenMascotaTieneVacunasDePerro(List<Vacuna> vacunas) {
        assertThat(vacunas).isNotNull();
        assertThat(vacunas).hasSize(3);
    }

    private List<Vacuna> whenTraerVacunasDePerro() {
        return repositorioCalendarioVacunacion.getVacunas("perro");
    }

    private void givenMascotaConVacunasDePerro() {
        Vacuna vacuna1 = new Vacuna()
                .setTipo("perro")
                .setMeses(1)
                .setTitulo("Moquillo")
                .setId(1L);

        Vacuna vacuna2 = new Vacuna()
                .setTipo("perro")
                .setMeses(2)
                .setTitulo("Polivalente Canina")
                .setId(1L);

        Vacuna vacuna3 = new Vacuna()
                .setTipo("perro")
                .setMeses(3)
                .setTitulo("Traqueobronquitis")
                .setId(1L);

        session().save(vacuna1);
        session().save(vacuna2);
        session().save(vacuna3);
    }

    private void thenUsuarioTieneSoloMascotaPerro(List<Mascota> mascotas) {
        assertThat(mascotas).isNotNull();
        assertThat(mascotas).hasSize(1);
        assertThat(mascotas.get(0).getTipo()).isEqualTo("perro");
    }

    private List<Mascota> whenTraerMascotaPerro() {
        return repositorioCalendarioVacunacion.getMascotas(1L);
    }

    private void givenUsuarioConMascotaPerro() {
        Mascota mascota = new Mascota()
                .setId(1L)
                .setFechaNacimiento(Date.from(Instant.parse("2020-12-03T10:15:30.00Z")))
                .setNombre("Pipi")
                .setTipo("perro")
                .setUserId(1L);
        session().save(mascota);
    }
}
