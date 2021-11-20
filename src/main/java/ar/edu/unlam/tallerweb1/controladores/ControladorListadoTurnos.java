package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.servicios.ServicioListadoTurno;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorListadoTurnos {

    private ServicioListadoTurno servicioListadoTurno;
    private ServicioNotificacion servicioNotificacion;

    @Autowired
    public ControladorListadoTurnos(ServicioListadoTurno servicioListadoTurno, ServicioNotificacion servicioNotificacion) {
        this.servicioListadoTurno = servicioListadoTurno;
        this.servicioNotificacion = servicioNotificacion;
    }

    @RequestMapping(path="/listado-turnos", method= RequestMethod.GET)
    public ModelAndView irAListadoDeTurnos(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Long userId = (Long) request.getSession().getAttribute("userId");

        model.put("turnos", getListadoDeTurnos(userId));
        model.put("notificaciones", getListadoDeNotificaciones(userId));

        return new ModelAndView("listado-turnos", model);
    }

    private List<Notificacion>  getListadoDeNotificaciones(Long userId) {
//        return servicioNotificacion.getNotificaciones(userId);
        List<Notificacion> notificaciones = new ArrayList<>();
        notificaciones.add(new Notificacion("Hola"));
        notificaciones.add(new Notificacion("Chau"));
        notificaciones.add(new Notificacion("Ver más"));
        return notificaciones;
    }

    public List<Turno> getListadoDeTurnos(Long userId) {
        return servicioListadoTurno.getListadoDeTurnos(userId);
    }
}
