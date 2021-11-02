package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Paseador;
import ar.edu.unlam.tallerweb1.servicios.ServicioPaseador;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioPaseadorImpl implements ServicioPaseador {
    @Override
    public Coordenadas obtenerCoordenadasDentroDeUnObjeto(String coordenadasString) {
        String[] coordenadasSplit=coordenadasString.split(",");
        Double latitud=Double.parseDouble(coordenadasSplit[0].trim());
        Double longitud=Double.parseDouble(coordenadasSplit[1].trim());
        return new Coordenadas(coordenadasString, latitud, longitud);
    }

    @Override
    public Paseador obtenerPaseador(Long id) {
        return null;
    }

    @Override
    public List<Paseador> obtenerListaDePaseadoresCercanos(Coordenadas coordenadasObject) {
        return null;
    }
}
