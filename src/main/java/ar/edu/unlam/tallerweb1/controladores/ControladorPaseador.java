package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.ServicioPaseador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorPaseador {
    private ServicioPaseador servicioPaseador;

    @Autowired
    public ControladorPaseador(ServicioPaseador servicioPaseador) {
        this.servicioPaseador=servicioPaseador;
    }

    @RequestMapping(path = "/ver-paseadores", method = RequestMethod.POST)
    public ModelAndView recibirLasCoordenadasDeUbicacion(@RequestParam String coordenadas) {
        ModelMap model=new ModelMap();
        Coordenadas coordenadaObject=servicioPaseador.obtenerCoordenadasDentroDeUnObjeto(coordenadas);
        model.put("coordenadas", coordenadaObject);
        return new ModelAndView("paseador-mapa", model);
    }

    @RequestMapping("/paseador")
    public ModelAndView verPaginaDePaseador() {
        return new ModelAndView("paseador-inicio");
    }
}
