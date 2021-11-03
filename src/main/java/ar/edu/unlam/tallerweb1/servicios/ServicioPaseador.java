package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Paseador;

import java.util.List;

public interface ServicioPaseador {
    Paseador obtenerPaseador(Long id);

    Double calcularPuntosDeDiferencia(Double puntos, Integer distancia);

    List<Paseador> obtenerListaDePaseadoresCercanos(Double latitud, Double longitud, Integer distancia);
}
