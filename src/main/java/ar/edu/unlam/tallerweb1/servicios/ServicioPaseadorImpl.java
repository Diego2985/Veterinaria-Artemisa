package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Paseador;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPaseador;
import ar.edu.unlam.tallerweb1.servicios.ServicioPaseador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioPaseadorImpl implements ServicioPaseador {
    private RepositorioPaseador repositorioPaseador;

    @Autowired
    public ServicioPaseadorImpl(RepositorioPaseador repositorioPaseador){
        this.repositorioPaseador=repositorioPaseador;
    }

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
        List<Paseador> todosLosPaseadores=repositorioPaseador.obtenerPaseadores();
        List<Paseador> paseadoresCercanos=new ArrayList<>();
//        for (Paseador paseador: todosLosPaseadores
//             ) {
//            if(Double.parseDouble(paseador.getCoordenadas())<=coordenadasObject.getLatitud()-0.01D){
//                paseadoresCercanos.add(paseador);
//            }
//        }
        return paseadoresCercanos;
    }
}
