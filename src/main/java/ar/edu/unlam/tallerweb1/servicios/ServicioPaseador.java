package ar.edu.unlam.tallerweb1.servicios;
import ar.edu.unlam.tallerweb1.excepciones.DatosCambiadosException;
import ar.edu.unlam.tallerweb1.excepciones.PaseadorConCantMaxDeMascotasException;
import ar.edu.unlam.tallerweb1.excepciones.PaseoIniciadoException;
import ar.edu.unlam.tallerweb1.excepciones.PaseoNoExistenteException;
import ar.edu.unlam.tallerweb1.modelo.Paseador;
import ar.edu.unlam.tallerweb1.modelo.RegistroPaseo;
import java.util.List;

public interface ServicioPaseador {
    Paseador obtenerPaseador(Long id);

    Double calcularPuntosDeDiferencia(Double puntos, Integer distancia);

    List<Paseador> obtenerListaDePaseadoresCercanos(Double latitud, Double longitud, Integer distancia);

    Paseador obtenerPaseador(Long idPaseador, Boolean chequeoCantidadMaxima) throws PaseadorConCantMaxDeMascotasException;

    RegistroPaseo crearRegistroDePaseo(Paseador paseador, Long idUsuario);

    RegistroPaseo actualizarRegistroDePaseo(Long idRegistro, Long idPaseador, Long idUsuario, Integer estado) throws DatosCambiadosException;

    RegistroPaseo obtenerRegistroDePaseo(Long idRegistroPaseo);

    RegistroPaseo verificarSiUnUsuarioTieneUnPaseoActivo(Long userId) throws PaseoIniciadoException, PaseoNoExistenteException;
}
