package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Turno;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ControladorCalendarioVacunacion {

    @RequestMapping(path = "/calendario-vacunacion", method = RequestMethod.GET)
    public ModelAndView irACalendarioVacunacion() {
        ModelMap model = new ModelMap();

        List<Turno> turnos = new ArrayList<>();
        Turno turno = new Turno();
        turno.setFecha(new Date());
        turno.setHora("10");
        turnos.add(turno);

        Turno turno2 = new Turno();
        turno2.setFecha(new Date());
        turno2.setHora("12");
        turnos.add(turno2);

        Turno turno3 = new Turno();
        turno3.setFecha(new Date());
        turno3.setHora("14");
        turnos.add(turno3);

        model.put("turnos", turnos);
        model.put("turno", turno);

        return new ModelAndView("calendario-vacunacion", model);
    }
}
