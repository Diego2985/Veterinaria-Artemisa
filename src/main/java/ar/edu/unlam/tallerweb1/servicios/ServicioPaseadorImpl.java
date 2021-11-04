package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.converter.Coordenadas;
import ar.edu.unlam.tallerweb1.converter.DatosTiempo;
import ar.edu.unlam.tallerweb1.excepciones.PaseadorConCantMaxDeMascotasException;
import ar.edu.unlam.tallerweb1.modelo.Paseador;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPaseador;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
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
        final String uri = "https://route.ls.hereapi.com/routing/7.2/calculateroute.json?apiKey=41cx0azEXC6uud3WIi1gIPI3A-nysczi2ogguQ6UQOM&waypoint0=geo!"+paseador.getLatitud()+","+paseador.getLongitud()+"&waypoint1=geo!"+usuario.getLatitud()+","+usuario.getLongitud()+"&mode=fastest;pedestrian";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        ObjectMapper mapper=new ObjectMapper();
        DatosTiempo datosTiempo=mapper.readValue(result,DatosTiempo.class);
        return datosTiempo;
    }

    @Override
    public Paseador obtenerPaseador(Long id, Boolean chequeoCantidadMaxima) throws PaseadorConCantMaxDeMascotasException {
        Paseador paseador=repositorioPaseador.obtenerUnPaseador(id);
        if(chequeoCantidadMaxima && paseador.getCantidadActual()>=paseador.getCantidadMaxima())
            throw new PaseadorConCantMaxDeMascotasException();
        return paseador;
    }

    public Double calcularPuntosDeDiferencia(Double puntos, Integer distancia) {
        Integer medidaTierra = 6378137;
        Double puntosMax = puntos + ((180 / Math.PI) * ((double) distancia / medidaTierra));
        Double diferencia = puntosMax - puntos;
        return diferencia;
    }
}
