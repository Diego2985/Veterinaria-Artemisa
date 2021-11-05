package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.converter.Coordenadas;
import ar.edu.unlam.tallerweb1.converter.DatosTiempo;
import ar.edu.unlam.tallerweb1.converter.Ubicacion;
import ar.edu.unlam.tallerweb1.excepciones.PaseadorConCantMaxDeMascotasException;
import ar.edu.unlam.tallerweb1.modelo.Paseador;

import java.io.IOException;
import java.util.List;

public interface ServicioPaseador {
    Paseador obtenerPaseador(Long id);

    Double calcularPuntosDeDiferencia(Double puntos, Integer distancia);

    List<Paseador> obtenerListaDePaseadoresCercanos(Double latitud, Double longitud, Integer distancia);

    DatosTiempo obtenerDistanciaYTiempo(Coordenadas usuario, Coordenadas paseador) throws IOException;

    String obtenerImagenDeRutaDePaseadorAUsuario(Coordenadas usuario, Coordenadas paseador) throws IOException;

    Paseador obtenerPaseador(Long idPaseador, Boolean chequeoCantidadMaxima) throws PaseadorConCantMaxDeMascotasException;

    Ubicacion obtenerDireccionDeUbicacionActual(Double latitud, Double longitud) throws IOException;
}
