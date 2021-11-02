package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.Coordenadas;
import ar.edu.unlam.tallerweb1.modelo.Paseador;

import java.util.List;

public interface ServicioPaseador {
    Paseador obtenerPaseador(Long id);

    List<Paseador> obtenerListaDePaseadoresCercanos(Coordenadas coordenadasObject);
}
