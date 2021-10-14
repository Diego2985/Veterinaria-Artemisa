package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.DatosTurno;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.servicios.ServicioListadoTurno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorListadoTurnos {

    private ServicioListadoTurno servicioListadoTurno;

    @Autowired
    public ControladorListadoTurnos(ServicioListadoTurno servicioListadoTurno) {
        this.servicioListadoTurno = servicioListadoTurno;
    }

    @RequestMapping(path="/listado-turnos", method= RequestMethod.GET)
    public ModelAndView irAListadoDeTurnos() {
        ModelMap model = new ModelMap();

        String fechaDesde = servicioListadoTurno.getFechaDesde();
        String fechaHasta = servicioListadoTurno.getFechaHasta();

        DatosTurno datosTurno = new DatosTurno()
                .setFechaDesde(fechaDesde)
                .setFechaHasta(fechaHasta);

        model.put("datosTurno", datosTurno);
        model.put("turnos", getListadoDeTurnos());

        return new ModelAndView("listado-turnos", model);
    }

    public List<Turno> getListadoDeTurnos() {
        return servicioListadoTurno.getListadoDeTurnos();
    }
}
