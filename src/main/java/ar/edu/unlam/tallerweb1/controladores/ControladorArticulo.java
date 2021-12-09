package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioArticulo;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorArticulo {

    private ServicioArticulo servicioArticulo;
    private ServicioNotificacion servicioNotificacion;

    @Autowired
    public ControladorArticulo(ServicioArticulo servicioArticulo, ServicioNotificacion servicioNotificacion) {
        this.servicioArticulo = servicioArticulo;
        this.servicioNotificacion = servicioNotificacion;
    }

    @RequestMapping(path = "/articulos", method = RequestMethod.GET)
    public ModelAndView irADetalleArticulo(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");

        ModelMap model = new ModelMap();
        model.put("articulos", servicioArticulo.getArticulos());
        model.put("notificaciones", getListadoDeNotificaciones(userId));

        return new ModelAndView("articulos", model);
    }

    @RequestMapping(path = "/articulos", method = RequestMethod.POST)
    public ModelAndView buscarArticulosPorNombre(@RequestParam(required = false) String busqueda) {
        if(busqueda != null && busqueda.trim().length()>0){
            ModelMap model = new ModelMap();
            model.put("articulos", servicioArticulo.buscarArticulosPorNombre(busqueda));
            return new ModelAndView("articulos", model);
        }

        return new ModelAndView("redirect:articulos");
    }

    @RequestMapping(path = "/notificacion-articulo", method = RequestMethod.POST)
    public ModelAndView setearNotificacionLeida(@RequestParam Long idNotificacion, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Long userId = (Long) request.getSession().getAttribute("userId");

        servicioNotificacion.update(idNotificacion, userId);

        model.put("articulos", servicioArticulo.getArticulos());
        model.put("notificaciones", getListadoDeNotificaciones(userId));
        return new ModelAndView("articulos", model);
    }

    @RequestMapping(path = "/setear-articulo-mostrado", method = RequestMethod.POST)
    public ModelAndView setearArticuloMostrado(@RequestParam Long idArticulo, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Long userId = (Long) request.getSession().getAttribute("userId");

        servicioArticulo.update(idArticulo);

        model.put("articulos", servicioArticulo.getArticulos());
        model.put("notificaciones", getListadoDeNotificaciones(userId));
        return new ModelAndView("articulos", model);
    }

    private List<Notificacion> getListadoDeNotificaciones(Long userId) {
        return servicioNotificacion.getNotificaciones(userId);
    }
}
