package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Recompensa;
import ar.edu.unlam.tallerweb1.modelo.TipoRecompensa;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.servicios.ServicioListadoTurno;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioRecompensa;
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
public class ControladorListadoTurnos {

    private ServicioListadoTurno servicioListadoTurno;
    private ServicioNotificacion servicioNotificacion;
    private ServicioRecompensa servicioRecompensa;

    @Autowired
    public ControladorListadoTurnos(ServicioListadoTurno servicioListadoTurno, ServicioNotificacion servicioNotificacion, ServicioRecompensa servicioRecompensa) {
        this.servicioListadoTurno = servicioListadoTurno;
        this.servicioNotificacion = servicioNotificacion;
        this.servicioRecompensa = servicioRecompensa;
    }

    @RequestMapping(path="/listado-turnos", method= RequestMethod.GET)
    public ModelAndView irAListadoDeTurnos(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Long userId = (Long) request.getSession().getAttribute("userId");

        model.put("turnos", getListadoDeTurnos(userId));
        model.put("notificaciones", getListadoDeNotificaciones(userId));
        model.put("recompensa", getRecompensa(userId));

        return new ModelAndView("listado-turnos", model);
    }

    @RequestMapping(path="/listado-turnos", method= RequestMethod.POST)
    public ModelAndView setearNotificacionLeida(@RequestParam Long idNotificacion, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Long userId = (Long) request.getSession().getAttribute("userId");

        servicioNotificacion.update(idNotificacion, userId);

        model.put("turnos", getListadoDeTurnos(userId));
        model.put("notificaciones", getListadoDeNotificaciones(userId));
        model.put("recompensa", getRecompensa(userId));

        return new ModelAndView("listado-turnos", model);
    }

    private List<Notificacion>  getListadoDeNotificaciones(Long userId) {
        return servicioNotificacion.getNotificaciones(userId);
    }

    public List<Turno> getListadoDeTurnos(Long userId) {
        return servicioListadoTurno.getListadoDeTurnos(userId);
    }

    private Recompensa getRecompensa(Long userId) {
        return servicioRecompensa.obtenerRecompensas(userId, TipoRecompensa.TURNO_GRATIS)
                .stream().findFirst().orElse(null);
    }
}
