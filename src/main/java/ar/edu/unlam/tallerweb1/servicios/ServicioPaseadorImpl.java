package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.converter.Coordenadas;
import ar.edu.unlam.tallerweb1.converter.DatosTiempo;
import ar.edu.unlam.tallerweb1.converter.Ubicacion;
import ar.edu.unlam.tallerweb1.excepciones.PaseadorConCantMaxDeMascotasException;
import ar.edu.unlam.tallerweb1.modelo.Paseador;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPaseador;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Service
public class ServicioPaseadorImpl implements ServicioPaseador {
    private RepositorioPaseador repositorioPaseador;

    @Autowired
    public ServicioPaseadorImpl(RepositorioPaseador repositorioPaseador) {
        this.repositorioPaseador = repositorioPaseador;
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

    private String getImageFromAPI(String uriImagen) throws UnsupportedEncodingException {
        RestTemplate restTemplate = new RestTemplate();
        byte[] imageGet=restTemplate.getForObject(uriImagen, byte[].class);
        byte[] encodeBase64 = Base64.getEncoder().encode(imageGet);
        String base64Encoded = new String(encodeBase64, "UTF-8");
        return base64Encoded;
    }

    @Override
    public Paseador obtenerPaseador(Long id, Boolean chequeoCantidadMaxima) throws PaseadorConCantMaxDeMascotasException {
        Paseador paseador=repositorioPaseador.obtenerUnPaseador(id);
        if(chequeoCantidadMaxima && paseador.getCantidadActual()>=paseador.getCantidadMaxima())
            throw new PaseadorConCantMaxDeMascotasException();
        return paseador;
    }

    @Override
    public Ubicacion obtenerDireccionDeUbicacionActual(Double latitud, Double longitud) throws IOException {
        final String uri = "https://revgeocode.search.hereapi.com/v1/revgeocode?at="+latitud+","+longitud+"&apiKey=41cx0azEXC6uud3WIi1gIPI3A-nysczi2ogguQ6UQOM";
        String result = obtenerJson(uri);
        ObjectMapper mapper=new ObjectMapper();
        Ubicacion ubicacion=mapper.readValue(result,Ubicacion.class);
        return ubicacion;
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
