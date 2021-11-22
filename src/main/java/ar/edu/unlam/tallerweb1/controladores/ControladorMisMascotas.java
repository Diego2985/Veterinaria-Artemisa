package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.servicios.ServicioMisMascotas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorMisMascotas {

    private final ServicioMisMascotas servicioMisMascotas;

    @Autowired
    public ControladorMisMascotas(ServicioMisMascotas servicioMisMascotas) {
        this.servicioMisMascotas = servicioMisMascotas;
    }

    @RequestMapping(path="/mis-mascotas", method= RequestMethod.GET)
    public ModelAndView irAMisMascotas(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Long userId = (Long) request.getSession().getAttribute("userId");

        model.put("mascotas", getMisMascotas(userId));

        return new ModelAndView("mis-mascotas", model);
    }

    private List<Mascota> getMisMascotas(Long userId) {
        return servicioMisMascotas.obtenerMascotas(userId);
    }
}
