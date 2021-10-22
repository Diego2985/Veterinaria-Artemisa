package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.ServicioArticulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorArticulo {

    @Autowired
    private ServicioArticulo servicioArticulo;
    @Autowired
    public ControladorArticulo(ServicioArticulo servicioArticulo) {
        this.servicioArticulo = servicioArticulo;
    }

    @RequestMapping(path="/articulos", method= RequestMethod.GET)
    public ModelAndView irADetalleArticulo() {
        ModelMap model = new ModelMap();
        model.put("articulos", servicioArticulo.getArticulos());

        return new ModelAndView("articulos", model);
    }

    @RequestMapping(path="buscar-articulo", method = RequestMethod.POST)
    public ModelAndView buscarArticulosPorNombre(@RequestParam(required = false) String busqueda) {
        ModelMap model=new ModelMap();
        model.put("articulos", servicioArticulo.buscarArticulosPorNombre(busqueda));
        return new ModelAndView("articulos", model);
    }
}
