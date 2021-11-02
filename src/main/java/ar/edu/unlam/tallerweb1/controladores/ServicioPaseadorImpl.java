package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.ServicioPaseador;
import org.springframework.stereotype.Service;

@Service
public class ServicioPaseadorImpl implements ServicioPaseador {
    @Override
    public Coordenadas obtenerCoordenadasDentroDeUnObjeto(String coordenadasString) {
        String[] coordenadasSplit=coordenadasString.split(",");
        Double latitud=Double.parseDouble(coordenadasSplit[0]);
        Double longitud=Double.parseDouble(coordenadasSplit[1]);
        return new Coordenadas(coordenadasString, latitud, longitud);
    }
}
