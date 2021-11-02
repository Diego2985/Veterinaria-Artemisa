package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.ServicioPaseador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public class ControladorPaseador {
    private ServicioPaseador servicioPaseador;

    @Autowired
    public ControladorPaseador(ServicioPaseador servicioPaseador) {
        this.servicioPaseador=servicioPaseador;
    }

    public ModelAndView recibirLasCoordenadasDeUbicacion(String coordenadas) {
        ModelMap model=new ModelMap();
        Coordenadas coordenadaObject=servicioPaseador.obtenerCoordenadasDentroDeUnObjeto(coordenadas);
        model.put("coordenadas", coordenadaObject);
        return new ModelAndView("paseador-mapa", model);
    }

    public ModelAndView verPaginaDePaseador() {
        return new ModelAndView("paseador-inicio");
    }
}
