package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.converter.Coordenadas;
import ar.edu.unlam.tallerweb1.excepciones.*;
import ar.edu.unlam.tallerweb1.modelo.Paseador;
import ar.edu.unlam.tallerweb1.modelo.RegistroPaseo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPaseador;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        registro.setHoraInicio(new Date());
        registro.setHoraFinal(new Date(registro.getHoraInicio().getTime() + (3600*1000)));
        registro.setEstado(estado);
        repositorioPaseador.actualizarEstadoDePaseo(registro);
        return registro;
    }

    @Override
    public RegistroPaseo obtenerRegistroDePaseo(Long idRegistroPaseo) {
        return repositorioPaseador.buscarUnRegistroDePaseo(idRegistroPaseo);
    }

    @Override
    public void verificarSiUnUsuarioTieneUnPaseoActivo(Long userId) throws PaseoIniciadoException, PaseoNoIniciadoException {
        RegistroPaseo registroPaseo = repositorioPaseador.buscarPaseoEnProcesoOActivoDeUnUsuario(userId);
        if(registroPaseo != null){
            if(registroPaseo.getEstado() == 0)
                throw new PaseoNoIniciadoException();
            else if(registroPaseo.getEstado() == 1)
                throw new PaseoIniciadoException();
        }

    }

    @Override
    public void validarEstadoEnSesion(Integer estado) throws PaseoIniciadoException, PaseoNoIniciadoException {
        if(estado != null){
            if(estado == 0)
                throw new PaseoNoIniciadoException();
            else if(estado == 1)
                throw new PaseoIniciadoException();
        }
    }

    @Override
    public RegistroPaseo obtenerRegistroDePaseoActivoOEnProceso(Long userId) {
        return repositorioPaseador.buscarPaseoEnProcesoOActivoDeUnUsuario(userId);
    }

    @Override
    public Boolean chequearAccesoCorrecto(HttpServletRequest request, Integer numeroEstado) {
        Integer estadoPaseo = (Integer) request.getSession().getAttribute("estadoPaseo");
        return estadoPaseo == null || estadoPaseo != numeroEstado;
    }

    @Override
    public Map<String, Coordenadas> obtenerCoordenadas(Double latitudUsuario, Double longitudUsuario, Paseador paseador) {
        Map<String, Coordenadas> coordenadas = new HashMap<>();
        coordenadas.put("usuario", new Coordenadas(latitudUsuario, longitudUsuario));
        coordenadas.put("paseador", new Coordenadas(paseador.getLatitud(), paseador.getLongitud()));
        return coordenadas;
    }

    public Double calcularPuntosDeDiferencia(Double puntos, Integer distancia) {
        Integer medidaTierra = 6378137;
        Double puntosMax = puntos + ((180 / Math.PI) * ((double) distancia / medidaTierra));
        Double diferencia = puntosMax - puntos;
        return diferencia;
    }
}
