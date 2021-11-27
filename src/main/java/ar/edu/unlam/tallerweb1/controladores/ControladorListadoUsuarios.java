package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorListadoUsuarios {

    private final ServicioUsuarios servicioUsuarios;

    @Autowired
    public ControladorListadoUsuarios(ServicioUsuarios servicioUsuarios) {
        this.servicioUsuarios = servicioUsuarios;
    }

    @RequestMapping(path="/listado-usuarios", method= RequestMethod.GET)
    public ModelAndView irAListadoDeUsuarios(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Long userId = (Long) request.getSession().getAttribute("userId");

        model.put("usuarios", servicioUsuarios.getUsuarios(userId));

        return new ModelAndView("listado-usuarios", model);
    }
}
