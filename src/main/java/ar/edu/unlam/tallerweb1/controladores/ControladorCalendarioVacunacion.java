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

//        List<Vacuna> vacunas = new ArrayList<>();
//        Vacuna vacuna = new Vacuna();
//        vacuna.setTitulo("Vacuna Gripe - Pepe");
//        vacuna.setFecha("2021-11-01");
//        vacuna.setHora("10");
//        vacunas.add(vacuna);
//
//        Vacuna vacuna2 = new Vacuna();
//        vacuna2.setTitulo("Vacuna Doble - Pepe");
//        vacuna2.setFecha("2021-11-02");
//        vacuna2.setHora("12");
//        vacunas.add(vacuna2);
//
//        Vacuna vacuna3 = new Vacuna();
//        vacuna3.setTitulo("Vacuna Triple - Pepe");
//        vacuna3.setFecha("2021-11-03");
//        vacuna3.setHora("14");
//        vacunas.add(vacuna3);
//
        model.put("vacunas", vacunas);
//        model.put("vacuna", vacuna);

        return new ModelAndView("calendario-vacunacion", model);
    }
}
