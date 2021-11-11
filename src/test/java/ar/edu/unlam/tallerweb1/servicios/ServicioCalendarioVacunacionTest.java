package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Vacuna;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCalendarioVacunacion;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioCalendarioVacunacionTest {

    private RepositorioCalendarioVacunacion repositorioCalendarioVacunacion;
    private ServicioCalendarioVacunacion servicioCalendarioVacunacion;

    @Before
    public void init() {
        repositorioCalendarioVacunacion = mock(RepositorioCalendarioVacunacion.class);
        servicioCalendarioVacunacion = new ServicioCalendarioVacunacionImpl(repositorioCalendarioVacunacion);
    }

    @Test
    public void testDadoMascotaTipoPerroTraerSusVacunas() {
        givenUsuarioYMascota();
        List<Vacuna> vacunas = whenTraerSusVacunas();
        thenSoloVacunasDePerro(vacunas);
    }

    @Test
    public void testDadoMascotaTipoGatoTraerSusVacunas() {
        givenUsuarioYMascota();
        List<Vacuna> vacunas = whenTraerVacunasDeGato();
        thenSoloVacunasDeGato(vacunas);
    }

    private void thenSoloVacunasDeGato(List<Vacuna> vacunas) {
        assertThat(vacunas).isNotNull();
        assertThat(vacunas).hasSize(2);
    }

    private List<Vacuna> whenTraerVacunasDeGato() {
        when(repositorioCalendarioVacunacion.getMascotas(1L)).thenReturn(getListadoDemascotasDeTipoGato());
        when(repositorioCalendarioVacunacion.getVacunas("gato")).thenReturn(getListaDeVacuansParaGato());
        return servicioCalendarioVacunacion.getVacunas(1L);
    }

    private void thenSoloVacunasDePerro(List<Vacuna> vacunas) {
        assertThat(vacunas).isNotNull();
        assertThat(vacunas).hasSize(3);
    }

    private List<Vacuna> whenTraerSusVacunas() {
        when(repositorioCalendarioVacunacion.getMascotas(1L)).thenReturn(getListadoDemascotasDeTipoPerro());
        when(repositorioCalendarioVacunacion.getVacunas("perro")).thenReturn(getListaDeVacuansParaPerro());
        return servicioCalendarioVacunacion.getVacunas(1L);
    }

    private List<Vacuna> getListaDeVacuansParaGato() {
        List<Vacuna> vacunas = new ArrayList<>();
        Vacuna vacuna1 = new Vacuna()
                .setTipo("gato")
                .setMeses(2)
                .setTitulo("Doble");

        Vacuna vacuna2 = new Vacuna()
                .setTipo("gato")
                .setMeses(3)
                .setTitulo("Triple Virica");

        vacunas.add(vacuna1);
        vacunas.add(vacuna2);

        return vacunas;
    }

    private List<Mascota> getListadoDemascotasDeTipoGato() {
        List<Mascota> mascotas = new ArrayList<>();
        Mascota mascota = new Mascota().setTipo("gato")
                .setNombre("Naranja")
                .setFechaNacimiento(Date.from(Instant.parse("2020-12-03T10:15:30.00Z")));
        mascotas.add(mascota);

        return mascotas;
    }

    private List<Mascota> getListadoDemascotasDeTipoPerro() {
        List<Mascota> mascotas = new ArrayList<>();
        Mascota mascota = new Mascota().setTipo("perro")
                .setNombre("Pipi")
                .setFechaNacimiento(Date.from(Instant.parse("2020-12-03T10:15:30.00Z")));
        mascotas.add(mascota);

        return mascotas;
    }

    private void givenUsuarioYMascota() {}

    private List<Vacuna> getListaDeVacuansParaPerro() {
        List<Vacuna> vacunas = new ArrayList<>();
        Vacuna vacuna1 = new Vacuna()
                .setTipo("perro")
                .setMeses(1)
                .setTitulo("Moquillo");

        Vacuna vacuna2 = new Vacuna()
                .setTipo("perro")
                .setMeses(2)
                .setTitulo("Polivalente Canina");

        Vacuna vacuna3 = new Vacuna()
                .setTipo("perro")
                .setMeses(3)
                .setTitulo("Traqueobronquitis");

        vacunas.add(vacuna1);
        vacunas.add(vacuna2);
        vacunas.add(vacuna3);

        return vacunas;
    }
}
