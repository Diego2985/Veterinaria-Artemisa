package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Paseador;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPaseador;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServicioPaseadorTest {
    private static final Double LATITUD = -34.588902;
    private static final Double LONGITUD = -58.409851;
    private Paseador paseador1 = new Paseador();
    private Paseador paseador2 = new Paseador();
    private Paseador paseador3 = new Paseador();
    private RepositorioPaseador repositorioPaseador=mock(RepositorioPaseador.class);
    private ServicioPaseador servicioPaseador=new ServicioPaseadorImpl(repositorioPaseador);
    private Integer distancia=500;
    private Double diferenciaLatitud;
    private Double diferenciaLongitud;
    private List<Paseador> listaDePaseadores=new ArrayList<>();

    @Before
    public void init() {
        Double latPaseador = -34.58856;
        Double longPaseador = -58.41066;
        crearPaseador(paseador1, latPaseador, longPaseador);

        latPaseador = -34.585991;
        longPaseador = -58.407848;
        crearPaseador(paseador2, latPaseador, longPaseador);

        latPaseador = -34.58581;
        longPaseador = -58.41485;
        crearPaseador(paseador3, latPaseador, longPaseador);
    }

    private void crearPaseador(Paseador paseador, Double latitud, Double longitud) {
        paseador.setEstrellas(5);
        paseador.setLatitud(latitud);
        paseador.setLongitud(longitud);
    }

    @Test
    public void solicitarUnaListaDePaseadoresCercanos() {
        givenUnaListaDePaseadoresYDiferenciasDePuntosCalculadas();
        List<Paseador> paseadoresObtenidos = whenSolicitoLosPaseadores();
        thenDebeRetornarLosPaseadoresCercanos(paseadoresObtenidos);
    }

    private void givenUnaListaDePaseadoresYDiferenciasDePuntosCalculadas() {
        listaDePaseadores.add(paseador1);
        listaDePaseadores.add(paseador2);

        diferenciaLatitud=servicioPaseador.calcularPuntosDeDiferencia(LATITUD, distancia);
        diferenciaLongitud=servicioPaseador.calcularPuntosDeDiferencia(LONGITUD, distancia);

        when(repositorioPaseador.obtenerPaseadoresCercanos(LATITUD, LONGITUD, diferenciaLatitud, diferenciaLongitud)).thenReturn(listaDePaseadores);
    }

    private List<Paseador> whenSolicitoLosPaseadores() {
        return servicioPaseador.obtenerListaDePaseadoresCercanos(LATITUD, LONGITUD, distancia);
    }

    private void thenDebeRetornarLosPaseadoresCercanos(List<Paseador> paseadoresObtenidos) {
        assertThat(paseadoresObtenidos).hasSize(2);
        assertThat(paseadoresObtenidos).contains(paseador1);
        assertThat(paseadoresObtenidos).contains(paseador2);
        assertThat(paseadoresObtenidos).doesNotContain(paseador3);
    }
}
