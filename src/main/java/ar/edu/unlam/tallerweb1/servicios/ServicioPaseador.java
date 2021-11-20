package ar.edu.unlam.tallerweb1.servicios;
import ar.edu.unlam.tallerweb1.converter.Coordenadas;
import ar.edu.unlam.tallerweb1.converter.DatosTiempo;
import ar.edu.unlam.tallerweb1.excepciones.*;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Paseador;
import ar.edu.unlam.tallerweb1.modelo.RegistroPaseo;
import ar.edu.unlam.tallerweb1.converter.Ubicacion;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface ServicioPaseador {
    Paseador obtenerPaseador(Long id);

    Double calcularPuntosDeDiferencia(Double puntos, Integer distancia);

    List<Paseador> obtenerListaDePaseadoresCercanos(Double latitud, Double longitud, Integer distancia);

    Paseador obtenerPaseador(Long idPaseador, Boolean chequeoCantidadMaxima) throws PaseadorConCantMaxDeMascotasException;

    RegistroPaseo crearRegistroDePaseo(Paseador paseador, Long idUsuario, Mascota mascota);

    RegistroPaseo actualizarRegistroDePaseo(Long idRegistro, Long idPaseador, Long idUsuario, Integer estado) throws DatosCambiadosException;

    RegistroPaseo obtenerRegistroDePaseo(Long idRegistroPaseo);

    void verificarSiUnUsuarioTieneUnPaseoActivo(Long userId) throws PaseoIniciadoException, PaseoNoIniciadoException;

    void validarEstadoEnSesion(Integer idRegistroPaseo) throws PaseoIniciadoException, PaseoNoIniciadoException;

    RegistroPaseo obtenerRegistroDePaseoActivoOEnProceso(Long userId);

    Boolean chequearAccesoCorrecto(HttpServletRequest request, Integer numeroEstado);

    Map<String, Coordenadas> obtenerCoordenadas(Double latitud, Double longitud, Paseador paseador);

    Ubicacion obtenerDireccionDeUbicacionActual(Double latitud, Double longitud) throws IOException;

    DatosTiempo obtenerDistanciaYTiempo(Coordenadas usuario, Coordenadas paseador) throws IOException;

    String obtenerImagenDeRutaDePaseadorAUsuario(Coordenadas usuario, Coordenadas paseador) throws UnsupportedEncodingException;

    String obtenerImagenDePosicionDelPaseador(Long id) throws UnsupportedEncodingException;

    Map<String, List<RegistroPaseo>> obtenerTodosLosRegistrosDePaseoDelUsuario(Long userId);

    Map<Long, DatosTiempo> obtenerDatosDeTiempoYPosicion(List<RegistroPaseo> paseos, Double latitudUsuario, Double longitudUsuario) throws IOException;
}
