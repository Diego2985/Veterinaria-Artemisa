package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Paseador;

import java.util.List;

public interface RepositorioPaseador {
    List<Paseador> obtenerPaseadores();

    List<Paseador> obtenerPaseadoresCercanos(Double latitud, Double longitud, Double diferenciaLatitud, Double diferenciaLongitud);
}
