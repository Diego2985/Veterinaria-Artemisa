package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Paseadores;
import ar.edu.unlam.tallerweb1.excepciones.PaseadorConCantMaxDeMascotasException;
import ar.edu.unlam.tallerweb1.modelo.Paseador;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPaseador;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServicioPaseadorTest {
    private static final Double LATITUD = -34.588902;
    private static final Double LONGITUD = -58.409851;
    private RepositorioPaseador repositorioPaseador = mock(RepositorioPaseador.class);
    private RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
    private ServicioPaseador servicioPaseador = new ServicioPaseadorImpl(repositorioPaseador, repositorioUsuario);
    private Integer distancia = 500;
    private Double diferenciaLatitud;
    private Double diferenciaLongitud;
    private List<Paseador> listaDePaseadores = Paseadores.crearPaseadores();

    @Test
    public void solicitarUnaListaDePaseadoresCercanos() {
        givenUnaListaDePaseadoresYDiferenciasDePuntosCalculadas();
        List<Paseador> paseadoresObtenidos = whenSolicitoLosPaseadores();
        thenDebeRetornarLosPaseadoresCercanos(paseadoresObtenidos);
    }

    private void givenUnaListaDePaseadoresYDiferenciasDePuntosCalculadas() {
        diferenciaLatitud = servicioPaseador.calcularPuntosDeDiferencia(LATITUD, distancia);
        diferenciaLongitud = servicioPaseador.calcularPuntosDeDiferencia(LONGITUD, distancia);

        listaDePaseadores.remove(2);

        when(repositorioPaseador.obtenerPaseadoresCercanos(LATITUD, LONGITUD, diferenciaLatitud, diferenciaLongitud)).thenReturn(listaDePaseadores);
    }

    private List<Paseador> whenSolicitoLosPaseadores() {
        return servicioPaseador.obtenerListaDePaseadoresCercanos(LATITUD, LONGITUD, distancia);
    }

    private void thenDebeRetornarLosPaseadoresCercanos(List<Paseador> paseadoresObtenidos) {
        assertThat(paseadoresObtenidos).hasSize(2);
        assertThat(paseadoresObtenidos).contains(listaDePaseadores.get(0));
        assertThat(paseadoresObtenidos).contains(listaDePaseadores.get(1));
    }

    @Test
    public void obtenerUnPaseador() {
        Paseador paseadorEsperado = givenUnPaseador();
        Paseador paseadorObtenido=whenSolicitoUnPaseador(paseadorEsperado.getId());
        thenDebeRetornarmeElPaseadorSolicitado(paseadorEsperado, paseadorObtenido);
    }

    private Paseador givenUnPaseador() {
        Paseador unPaseador= listaDePaseadores.get(0);

        when(repositorioPaseador.obtenerUnPaseador(unPaseador.getId())).thenReturn(unPaseador);

        return unPaseador;
    }

    private Paseador whenSolicitoUnPaseador(Long id) {
        return servicioPaseador.obtenerPaseador(id);
    }

    private void thenDebeRetornarmeElPaseadorSolicitado(Paseador paseadorEsperado, Paseador paseadorObtenido) {
        assertThat(paseadorObtenido).isEqualTo(paseadorEsperado);
    }

    @Test(expected = PaseadorConCantMaxDeMascotasException.class)
    public void siELPaseadorLlegoALaCantMaxDebeSaltarLaExcepcion() throws PaseadorConCantMaxDeMascotasException {
        Paseador esperado=givenUnPaseadorConCantidadesActualYMax();
        whenSolicitoUnPaseadorChequeandoQueNoHayaLlegadoALaCantMax(esperado.getId());
    }

    private Paseador givenUnPaseadorConCantidadesActualYMax() {
        Paseador paseador=listaDePaseadores.get(0);
        paseador.setCantidadMaxima(10);
        paseador.setCantidadActual(10);

        when(repositorioPaseador.obtenerUnPaseador(paseador.getId())).thenReturn(paseador);

        return paseador;
    }

    private Paseador whenSolicitoUnPaseadorChequeandoQueNoHayaLlegadoALaCantMax(Long id) throws PaseadorConCantMaxDeMascotasException {
        return servicioPaseador.obtenerPaseador(id, true);
    }
}
