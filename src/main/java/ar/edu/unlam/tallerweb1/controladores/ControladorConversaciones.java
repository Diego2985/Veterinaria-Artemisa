package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.ServicioConversaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorConversaciones {

    private final ServicioConversaciones servicioConversaciones;

    @Autowired
    public ControladorConversaciones(ServicioConversaciones servicioConversaciones) {
        this.servicioConversaciones = servicioConversaciones;
    }

    @RequestMapping(path="/listado-conversaciones", method= RequestMethod.GET)
    public ModelAndView irAListadoDeConversaciones(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Long userId = (Long) request.getSession().getAttribute("userId");

        model.put("conversaciones", servicioConversaciones.conversaciones(userId));
        model.put("userId", userId);

        return new ModelAndView("listado-conversaciones", model);
    }
}
