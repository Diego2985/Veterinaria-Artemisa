package ar.edu.unlam.tallerweb1.servicios;
import ar.edu.unlam.tallerweb1.converter.Coordenadas;
import ar.edu.unlam.tallerweb1.excepciones.*;
import ar.edu.unlam.tallerweb1.modelo.Paseador;
import ar.edu.unlam.tallerweb1.modelo.RegistroPaseo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ServicioPaseador {
    Paseador obtenerPaseador(Long id);

    Double calcularPuntosDeDiferencia(Double puntos, Integer distancia);

    List<Paseador> obtenerListaDePaseadoresCercanos(Double latitud, Double longitud, Integer distancia);

    Paseador obtenerPaseador(Long idPaseador, Boolean chequeoCantidadMaxima) throws PaseadorConCantMaxDeMascotasException;

    RegistroPaseo crearRegistroDePaseo(Paseador paseador, Long idUsuario);

    RegistroPaseo actualizarRegistroDePaseo(Long idRegistro, Long idPaseador, Long idUsuario, Integer estado) throws DatosCambiadosException;

    RegistroPaseo obtenerRegistroDePaseo(Long idRegistroPaseo);

    void verificarSiUnUsuarioTieneUnPaseoActivo(Long userId) throws PaseoIniciadoException, PaseoNoIniciadoException;

    void validarEstadoEnSesion(Integer idRegistroPaseo) throws PaseoIniciadoException, PaseoNoIniciadoException;

    RegistroPaseo obtenerRegistroDePaseoActivoOEnProceso(Long userId);

    Boolean chequearAccesoCorrecto(HttpServletRequest request, Integer numeroEstado);

    Map<String, Coordenadas> obtenerCoordenadas(Double latitud, Double longitud, Paseador paseador);
}
