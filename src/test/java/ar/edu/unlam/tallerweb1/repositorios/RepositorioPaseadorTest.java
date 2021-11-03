package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.Paseadores;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Paseador;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class RepositorioPaseadorTest extends SpringTest {
    private static final Double LATITUD = -34.588902;
    private static final Double LONGITUD = -58.409851;
    @Autowired
    private RepositorioPaseador repositorioPaseador;
    private Integer distancia;
    private Double diferenciaLatitud;
    private Double diferenciaLongitud;
    private List<Paseador> paseadores= Paseadores.crearPaseadores();

    private void crearPaseador(Paseador paseador, Double latitud, Double longitud) {
        paseador.setEstrellas(5);
        paseador.setLatitud(latitud);
        paseador.setLongitud(longitud);
    }

    private Double calcularDiferenciaPuntosLatLOLong(Double puntos, Integer distancia) {
        Double puntosMax = puntos + ((180 / Math.PI) * ((double) distancia / 6378137));
        Double diferencia = puntosMax - puntos;
        return diferencia;
    }

    @Test
    @Rollback
    @Transactional
    public void extraerLosPaseadoresCercanosPorUnaDistanciaMaxima() {
        givenUnaListaDePaseadoresYCalculoDeDiferenciaDeCoordenadas();
        List<Paseador> paseadoresCercanos = whenSolicitoLosPaseadores();
        thenDeberiaRetornarmeLosQueEstenAUnaDistanciaMaxima(paseadoresCercanos);
    }

    private void givenUnaListaDePaseadoresYCalculoDeDiferenciaDeCoordenadas() {
        distancia = 500;
        for (Paseador paseador: paseadores
             ) {
            session().save(paseador);
        }

        diferenciaLatitud = calcularDiferenciaPuntosLatLOLong(LATITUD, distancia);
        diferenciaLongitud = calcularDiferenciaPuntosLatLOLong(LATITUD, distancia);
    }

    private List<Paseador> whenSolicitoLosPaseadores() {
        return repositorioPaseador.obtenerPaseadoresCercanos(LATITUD, LONGITUD, diferenciaLatitud, diferenciaLongitud);
    }

    private void thenDeberiaRetornarmeLosQueEstenAUnaDistanciaMaxima(List<Paseador> paseadoresCercanos) {
        assertThat(paseadoresCercanos).hasSize(2);
        assertThat(paseadoresCercanos).contains(paseadores.get(0));
        assertThat(paseadoresCercanos).contains(paseadores.get(1));
        assertThat(paseadoresCercanos).doesNotContain(paseadores.get(2));
    }
}
