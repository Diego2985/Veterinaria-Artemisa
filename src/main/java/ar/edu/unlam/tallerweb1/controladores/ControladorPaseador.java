package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Paseador;
import ar.edu.unlam.tallerweb1.servicios.ServicioPaseador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorPaseador {
    private ServicioPaseador servicioPaseador;

    @Autowired
    public ControladorPaseador(ServicioPaseador servicioPaseador) {
        this.servicioPaseador=servicioPaseador;
    }

    @RequestMapping(path = "/ver-paseadores", method = RequestMethod.POST)
    public ModelAndView obtenerPaseadoresCercanosA500mts(@RequestParam Double latitud, @RequestParam Double longitud) {
        try {
            ModelMap model=new ModelMap();
            Integer distancia=500;
            List<Paseador> paseadores=servicioPaseador.obtenerListaDePaseadoresCercanos(latitud, longitud, distancia);
            model.put("paseadores", paseadores);
            model.put("latitud", latitud);
            model.put("longitud", longitud);
            return new ModelAndView("paseador-mapa", model);
        }
        catch (Exception e) {
            return new ModelAndView("error");
        }
    }

    @RequestMapping("/paseador")
    public ModelAndView verPaginaDePaseador() {
        return new ModelAndView("paseador-inicio");
    }

    @RequestMapping(path = "/contratar-paseador", method = RequestMethod.POST)
    public ModelAndView contratarAlPaseador(@RequestParam Long idPaseador) {
        ModelMap model=new ModelMap();
        Paseador paseador=servicioPaseador.obtenerPaseador(idPaseador);
        model.put("idPaseador", idPaseador);
        model.put("paseador", paseador);
        return new ModelAndView("paseador-exitoso", model);
    }
}
