package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorNotificaciones {

    private ServicioNotificacion servicioNotificacion;

    @Autowired
    public ControladorNotificaciones(ServicioNotificacion servicioNotificacion) {
        this.servicioNotificacion = servicioNotificacion;
    }

    @RequestMapping("/")
    public void setNotificacionLeida() {

    }
}
