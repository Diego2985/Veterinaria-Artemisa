package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.converter.Coordenadas;
import ar.edu.unlam.tallerweb1.converter.DatosTiempo;
import ar.edu.unlam.tallerweb1.converter.PaseoActivo;
import ar.edu.unlam.tallerweb1.excepciones.*;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Paseador;
import ar.edu.unlam.tallerweb1.modelo.RegistroPaseo;
import ar.edu.unlam.tallerweb1.converter.Ubicacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPaseador;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;

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
    public RegistroPaseo crearRegistroDePaseo(Paseador paseador, Long idUsuario, Mascota mascota, Double latitud, Double longitud) throws IOException {
        Usuario usuario = repositorioUsuario.buscarUsuarioPorId(idUsuario);
        Ubicacion domicilio = obtenerDireccionDeUbicacionActual(latitud, longitud);
        RegistroPaseo registro=new RegistroPaseo();
        registro.setPaseador(paseador);
        registro.setUsuario(usuario);
        registro.setMascota(mascota);
        registro.setLatitudUsuario(latitud);
        registro.setLongitudUsuario(longitud);
        registro.setDomicilio(domicilio.toString());
        repositorioPaseador.crearRegistroDePaseo(registro);
        mascota.setPaseoActivo(true);
        repositorioPaseador.cambiarEstadoDePaseoDePerro(mascota);
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

    @Override
    public Ubicacion obtenerDireccionDeUbicacionActual(Double latitud, Double longitud) throws IOException {
        final String uri = "https://revgeocode.search.hereapi.com/v1/revgeocode?at="+latitud+","+longitud+"&apiKey=41cx0azEXC6uud3WIi1gIPI3A-nysczi2ogguQ6UQOM";
        String result = obtenerJson(uri);
        ObjectMapper mapper=new ObjectMapper();
        Ubicacion ubicacion=mapper.readValue(result,Ubicacion.class);
        return ubicacion;
    }

    @Override
    public DatosTiempo obtenerDistanciaYTiempo(Coordenadas usuario, Coordenadas paseador) throws IOException {
        final String uri = "https://route.ls.hereapi.com/routing/7.2/calculateroute.json?apiKey=41cx0azEXC6uud3WIi1gIPI3A-nysczi2ogguQ6UQOM&waypoint0=geo!"+paseador.toString()+"&waypoint1=geo!"+usuario.toString()+"&mode=shortest;pedestrian";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        ObjectMapper mapper=new ObjectMapper();
        DatosTiempo datosTiempo=mapper.readValue(result,DatosTiempo.class);
        datosTiempo.setCoordenadasPaseador(paseador);
        datosTiempo.setCoordenadasUsuario(usuario);
        return datosTiempo;
    }

    @Override
    public String obtenerImagenDeRutaDePaseadorAUsuario(Coordenadas usuario, Coordenadas paseador) throws UnsupportedEncodingException {
        final String uriImagen="https://image.maps.ls.hereapi.com/mia/1.6/routing?apiKey=41cx0azEXC6uud3WIi1gIPI3A-nysczi2ogguQ6UQOM&waypoint0="+paseador.toString()+"&waypoint1="+usuario.toString()+"&poix0="+paseador.toString()+";00a3f2;00a3f2;11;.&poix1="+usuario.toString()+";white;white;11;.&lc=1652B4&lw=6";
        String imagenBase64 = getImageFromAPI(uriImagen);
        return imagenBase64;
    }

    @Override
    public String obtenerImagenDePosicionDelPaseador(Long id) throws UnsupportedEncodingException {
        Paseador paseador = repositorioPaseador.obtenerUnPaseador(id);
        return obtenerImagenDePosicion(paseador.getLatitud(), paseador.getLongitud());
    }

    private String obtenerImagenDePosicion(Double latitud, Double longitud) throws UnsupportedEncodingException {
        Coordenadas coordenadas = new Coordenadas(latitud, longitud);
        String uri = "https://image.maps.ls.hereapi.com/mia/1.6/mapview?apiKey=41cx0azEXC6uud3WIi1gIPI3A-nysczi2ogguQ6UQOM&i&c="+coordenadas.toString()+"&h=300&w=400&r=10";
        return getImageFromAPI(uri);
    }

    @Override
    public Map<String, List<RegistroPaseo>> obtenerTodosLosRegistrosDePaseoDelUsuario(Long userId) {
        List<RegistroPaseo> paseos = repositorioPaseador.obtenerTodosLosPaseosDeUnUsuario(userId);
        Map<String, List<RegistroPaseo>> paseosSeparados = new HashMap<>();
        paseosSeparados.put("proceso", new ArrayList<>());
        paseosSeparados.put("activos", new ArrayList<>());
        paseosSeparados.put("finalizados", new ArrayList<>());
        for (RegistroPaseo paseo: paseos) {
            switch (paseo.getEstado()){
                case 0: paseosSeparados.get("proceso").add(paseo);break;
                case 1: paseosSeparados.get("activos").add(paseo);break;
                default: paseosSeparados.get("finalizados").add(paseo);break;
            }
        }
        return paseosSeparados;
    }

    @Override
    public Map<Long, DatosTiempo> obtenerDatosDeTiempoYPosicion(List<RegistroPaseo> paseos, Double latitudUsuario, Double longitudUsuario) throws IOException {
        Map<Long, DatosTiempo> listaDatosTiempo = new HashMap<>();
        if(latitudUsuario != null && longitudUsuario != null){
            for (RegistroPaseo paseo : paseos) {
                Coordenadas paseador = new Coordenadas(paseo.getPaseador().getLatitud(), paseo.getPaseador().getLongitud());
                Coordenadas usuario = new Coordenadas(latitudUsuario, longitudUsuario);
                String imagen = obtenerImagenDeRutaDePaseadorAUsuario(usuario, paseador);
                DatosTiempo datosTiempo = obtenerDistanciaYTiempo(usuario, paseador);
                datosTiempo.setImagenRuta(imagen);
                listaDatosTiempo.put(paseo.getId(), datosTiempo);
            }
        }
        return listaDatosTiempo;
    }

    @Override
    public Map<Long, PaseoActivo> obtenerMasDatosDePaseosActivos(List<RegistroPaseo> paseos) throws UnsupportedEncodingException {
        Map<Long, PaseoActivo> mapPaseosActivos = new HashMap<>();
        for (RegistroPaseo paseo:paseos) {
            String imagen = obtenerImagenDePosicionDelPaseador(paseo.getPaseador().getId());
            String imgUsuario = obtenerImagenDePosicion(paseo.getLatitudUsuario(), paseo.getLongitudUsuario());
            Integer minutosRestantes = Math.toIntExact(((paseo.getHoraFinal().getTime() - new Date().getTime()) / 1000) / 60);
            PaseoActivo paseoActivo = new PaseoActivo(paseo, minutosRestantes, imagen, imgUsuario);
            mapPaseosActivos.put(paseo.getId(), paseoActivo);
        }
        return mapPaseosActivos;
    }

    @Override
    public void cambiarEstadoDePaseoDeMascota(Mascota mascota) {
        mascota.setPaseoActivo(!mascota.getPaseoActivo());
        repositorioPaseador.cambiarEstadoDePaseoDePerro(mascota);
    }

    @Override
    public List<RegistroPaseo> obtenerPaseosDeUnPaseador(Long userId, Integer estado) {
        Paseador paseador = repositorioPaseador.obtenerPaseadorPorIdUsuario(userId);
        return repositorioPaseador.obtenerPaseosDeUnPaseador(paseador.getId(), estado);
    }

    @Override
    public HashMap<Long, String> obtenerImagenesDeRutaADomicilios(List<RegistroPaseo> paseos) throws UnsupportedEncodingException {
        HashMap<Long, String> imagenes = new HashMap<>();
        for (RegistroPaseo paseo: paseos
             ) {
            Coordenadas usuario = new Coordenadas(paseo.getLatitudUsuario(), paseo.getLongitudUsuario());
            Coordenadas paseador = new Coordenadas(paseo.getPaseador().getLatitud(), paseo.getPaseador().getLongitud());
            String imagen = obtenerImagenDeRutaDePaseadorAUsuario(usuario, paseador);
            imagenes.put(paseo.getId(), imagen);
        }
        return imagenes;
    }

    private String getImageFromAPI(String uriImagen) throws UnsupportedEncodingException {
        RestTemplate restTemplate = new RestTemplate();
        byte[] imageGet=restTemplate.getForObject(uriImagen, byte[].class);
        byte[] encodeBase64 = Base64.getEncoder().encode(imageGet);
        String base64Encoded = new String(encodeBase64, "UTF-8");
        return base64Encoded;
    }

    private String obtenerJson(String uri) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }

    public Double calcularPuntosDeDiferencia(Double puntos, Integer distancia) {
        Integer medidaTierra = 6378137;
        Double puntosMax = puntos + ((180 / Math.PI) * ((double) distancia / medidaTierra));
        Double diferencia = puntosMax - puntos;
        return diferencia;
    }
}
