package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Vacuna;
import ar.edu.unlam.tallerweb1.servicios.ServicioCalendarioVacunacion;
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
public class ControladorCalendarioVacunacion {

    private ServicioCalendarioVacunacion servicioCalendarioVacunacion;
    private ServicioListadoTurno servicioListadoTurno;

    @Autowired
    public ControladorCalendarioVacunacion(ServicioCalendarioVacunacion servicioCalendarioVacunacion, ServicioListadoTurno servicioListadoTurno) {
        this.servicioCalendarioVacunacion = servicioCalendarioVacunacion;
        this.servicioListadoTurno = servicioListadoTurno;
    }

    @RequestMapping(path = "/calendario-vacunacion", method = RequestMethod.GET)
    public ModelAndView irACalendarioVacunacion(HttpServletRequest request) {
        ModelMap model = new ModelMap();

        Long userId = (Long) request.getSession().getAttribute("userId");
        List<Vacuna> vacunas = servicioCalendarioVacunacion.getVacunas(userId);
        List<Turno> turnos = servicioListadoTurno.getListadoDeTurnos(userId);

        model.put("vacunas", vacunas);
        model.put("turnos", turnos);

        return new ModelAndView("calendario-vacunacion", model);
    }
}
