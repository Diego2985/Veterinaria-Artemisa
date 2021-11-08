package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.excepciones.DatosCambiadosException;
import ar.edu.unlam.tallerweb1.excepciones.PaseadorConCantMaxDeMascotasException;
import ar.edu.unlam.tallerweb1.modelo.Paseador;
import ar.edu.unlam.tallerweb1.modelo.RegistroPaseo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPaseador;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServicioPaseadorImpl implements ServicioPaseador {
    private RepositorioPaseador repositorioPaseador;
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioPaseadorImpl(RepositorioPaseador repositorioPaseador, RepositorioUsuario repositorioUsuario) {
        this.repositorioPaseador = repositorioPaseador;
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public Paseador obtenerPaseador(Long id) {
        return repositorioPaseador.obtenerUnPaseador(id);
    }

    @Override
    public List<Paseador> obtenerListaDePaseadoresCercanos(Double latitud, Double longitud, Integer distancia) {
        Double diferenciaLatitud = calcularPuntosDeDiferencia(latitud, distancia);
        Double diferenciaLongitud = calcularPuntosDeDiferencia(longitud, distancia);
        List<Paseador> paseadoresCercanos = repositorioPaseador.obtenerPaseadoresCercanos(latitud, longitud, diferenciaLatitud, diferenciaLongitud);
        return paseadoresCercanos;
    }

    @Override
    public Paseador obtenerPaseador(Long id, Boolean chequeoCantidadMaxima) throws PaseadorConCantMaxDeMascotasException {
        Paseador paseador=repositorioPaseador.obtenerUnPaseador(id);
        if(chequeoCantidadMaxima && paseador.getCantidadActual()>=paseador.getCantidadMaxima())
            throw new PaseadorConCantMaxDeMascotasException();
        return paseador;
    }

    @Override
    public RegistroPaseo crearRegistroDePaseo(Paseador paseador, Long idUsuario) {
        Usuario usuario = repositorioUsuario.buscarUsuarioPorId(idUsuario);
        RegistroPaseo registro=new RegistroPaseo();
        registro.setPaseador(paseador);
        registro.setUsuario(usuario);
        repositorioPaseador.crearRegistroDePaseo(registro);
        return registro;
    }

    @Override
    public RegistroPaseo actualizarRegistroDePaseo(Long idRegistro, Long idPaseador, Long idUsuario, Integer estado) throws DatosCambiadosException {
        RegistroPaseo registro = repositorioPaseador.buscarUnRegistroDePaseo(idRegistro);
        if(registro.getPaseador().getId()!=idPaseador || registro.getUsuario().getId()!=idUsuario)
            throw new DatosCambiadosException();
        registro.setEstado(estado);
        repositorioPaseador.actualizarEstadoDePaseo(registro);
        return registro;
    }

    @Override
    public RegistroPaseo obtenerRegistroDePaseo(Long idRegistroPaseo) {
        return repositorioPaseador.buscarUnRegistroDePaseo(idRegistroPaseo);
    }

    public Double calcularPuntosDeDiferencia(Double puntos, Integer distancia) {
        Integer medidaTierra = 6378137;
        Double puntosMax = puntos + ((180 / Math.PI) * ((double) distancia / medidaTierra));
        Double diferencia = puntosMax - puntos;
        return diferencia;
    }
}
