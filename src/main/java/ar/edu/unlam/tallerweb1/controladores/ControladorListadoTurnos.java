package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.servicios.ServicioListadoTurno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorListadoTurnos {

    private ServicioListadoTurno servicioListadoTurno;

    @Autowired
    public ControladorListadoTurnos(ServicioListadoTurno servicioListadoTurno) {
        this.servicioListadoTurno = servicioListadoTurno;
    }

    @RequestMapping(path="/listado-turnos", method= RequestMethod.GET)
    public ModelAndView irAListadoDeTurnos(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Long userId = (Long) request.getSession().getAttribute("userId");

        model.put("turnos", getListadoDeTurnos(userId));

        return new ModelAndView("listado-turnos", model);
    }

    public List<Turno> getListadoDeTurnos(Long userId) {
        return servicioListadoTurno.getListadoDeTurnos(userId);
    }
}
