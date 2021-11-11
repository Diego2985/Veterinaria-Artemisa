package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Paseador;
import ar.edu.unlam.tallerweb1.modelo.RegistroPaseo;

import java.util.List;

public interface RepositorioPaseador {
    List<Paseador> obtenerPaseadores();

    List<Paseador> obtenerPaseadoresCercanos(Double latitud, Double longitud, Double diferenciaLatitud, Double diferenciaLongitud);

    Paseador obtenerUnPaseador(Long id);

    void crearRegistroDePaseo(RegistroPaseo registro);

    void actualizarEstadoDePaseo(RegistroPaseo registro);

    RegistroPaseo buscarUnRegistroDePaseo(Long idRegistro);

    RegistroPaseo buscarPaseoEnProcesoOActivoDeUnUsuario(Long userId);
}
