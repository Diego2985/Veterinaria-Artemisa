package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Paseadores;
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
    private RepositorioPaseador repositorioPaseador=mock(RepositorioPaseador.class);
    private ServicioPaseador servicioPaseador=new ServicioPaseadorImpl(repositorioPaseador);
    private Integer distancia=500;
    private Double diferenciaLatitud;
    private Double diferenciaLongitud;
    private List<Paseador> listaDePaseadores= Paseadores.crearPaseadores();

    @Test
    public void solicitarUnaListaDePaseadoresCercanos() {
        givenUnaListaDePaseadoresYDiferenciasDePuntosCalculadas();
        List<Paseador> paseadoresObtenidos = whenSolicitoLosPaseadores();
        thenDebeRetornarLosPaseadoresCercanos(paseadoresObtenidos);
    }

    private void givenUnaListaDePaseadoresYDiferenciasDePuntosCalculadas() {
        diferenciaLatitud=servicioPaseador.calcularPuntosDeDiferencia(LATITUD, distancia);
        diferenciaLongitud=servicioPaseador.calcularPuntosDeDiferencia(LONGITUD, distancia);

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
}
