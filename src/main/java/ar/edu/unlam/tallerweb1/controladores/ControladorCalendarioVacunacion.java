package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Vacuna;
import ar.edu.unlam.tallerweb1.servicios.ServicioCalendarioVacunacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorCalendarioVacunacion {

    private ServicioCalendarioVacunacion servicioCalendarioVacunacion;

    @Autowired
    public ControladorCalendarioVacunacion(ServicioCalendarioVacunacion servicioCalendarioVacunacion) {
        this.servicioCalendarioVacunacion = servicioCalendarioVacunacion;
    }

    @RequestMapping(path = "/calendario-vacunacion", method = RequestMethod.GET)
    public ModelAndView irACalendarioVacunacion(HttpServletRequest request) {
        ModelMap model = new ModelMap();

        Long userId = (Long) request.getSession().getAttribute("userId");
        List<Vacuna> vacunas = servicioCalendarioVacunacion.getVacunas(userId);

        model.put("vacunas", vacunas);

        return new ModelAndView("calendario-vacunacion", model);
    }
}
