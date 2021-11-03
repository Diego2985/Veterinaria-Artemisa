package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Paseador;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class RepositorioPaseadorTest extends SpringTest {
    private static final Double LATITUD = -34.588902;
    private static final Double LONGITUD = -58.409851;
    private Paseador paseador1 = new Paseador();
    private Paseador paseador2 = new Paseador();
    private Paseador paseador3 = new Paseador();
    @Autowired
    private RepositorioPaseador repositorioPaseador;
    private Integer distancia;
    private Double diferenciaLatitud;
    private Double diferenciaLongitud;

    @Before
    public void init() {
        Double latPaseador = -34.58856;
        Double longPaseador = -58.41066;
        crearPaseador(paseador1, latPaseador, longPaseador);

        latPaseador = -34.585991;
        longPaseador = -58.407848;
        crearPaseador(paseador2, latPaseador, longPaseador);

        latPaseador = -34.58224;
        longPaseador = -58.4101;
        crearPaseador(paseador3, latPaseador, longPaseador);
    }

    private void crearPaseador(Paseador paseador, Double latitud, Double longitud) {
        paseador.setEstrellas(5);
        paseador.setLatitud(latitud);
        paseador.setLongitud(longitud);
    }

    private Double calcularLatitudMaxima(Double latitud, Integer distancia) {
        Double distanciaDouble = (double) distancia;
        Double lat = latitud + ((180 / Math.PI) * (distanciaDouble / 6378137));
        Double diferencia = lat - latitud;
        return diferencia;
    }

    private Double calcularLongitudMaxima(Double latitud, Double longitud, Integer distancia) {
        Double lat0 = Math.cos(Math.PI / 180.0 * latitud);
        Double x = longitud + (180 / Math.PI) * ((double) distancia / 6378137) / Math.cos(lat0);
        Double diferencia = x - longitud;
        return diferencia;
    }

    @Test
    @Rollback
    @Transactional
    public void extraerLosPaseadoresCercanosPorUnaDistanciaMaxima() {
        givenUnaListaDePaseadores();
        List<Paseador> paseadoresCercanos = whenSolicitoLosPaseadores();
        thenDeberiaRetornarmeLosQueEstenAUnaDistanciaMaxima(paseadoresCercanos);
    }

    private void givenUnaListaDePaseadores() {
        distancia = 500;
        session().save(paseador1);
        session().save(paseador2);
        session().save(paseador3);

        diferenciaLatitud = calcularLatitudMaxima(LATITUD, distancia);
        diferenciaLongitud = calcularLongitudMaxima(LATITUD, LONGITUD, distancia);
    }

    private List<Paseador> whenSolicitoLosPaseadores() {
        return repositorioPaseador.obtenerPaseadoresCercanos(LATITUD, LONGITUD, diferenciaLatitud, diferenciaLongitud);
    }

    private void thenDeberiaRetornarmeLosQueEstenAUnaDistanciaMaxima(List<Paseador> paseadoresCercanos) {
        assertThat(paseadoresCercanos).hasSize(2);
    }
}
