package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Recompensa;
import ar.edu.unlam.tallerweb1.modelo.TipoRecompensa;
import ar.edu.unlam.tallerweb1.servicios.ServicioMisMascotas;
import ar.edu.unlam.tallerweb1.servicios.ServicioRecompensa;
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
    private final ServicioRecompensa servicioRecompensa;

    @Autowired
    public ControladorMisMascotas(ServicioMisMascotas servicioMisMascotas, ServicioRecompensa servicioRecompensa) {
        this.servicioMisMascotas = servicioMisMascotas;
        this.servicioRecompensa = servicioRecompensa;
    }

    @RequestMapping(path="/mis-mascotas", method= RequestMethod.GET)
    public ModelAndView irAMisMascotas(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Long userId = (Long) request.getSession().getAttribute("userId");

        List<Mascota> mascotas = getMisMascotas(userId);
        servicioRecompensa.generarRecompensa(userId, TipoRecompensa.ALIMENTO_GRATIS, (long) mascotas.size());

        model.put("mascotas", mascotas);
        model.put("recompensa", getRecompensa(userId));

        return new ModelAndView("mis-mascotas", model);
    }

    private List<Mascota> getMisMascotas(Long userId) {
        return servicioMisMascotas.obtenerMascotas(userId);
    }

    private Recompensa getRecompensa(Long userId) {
        return servicioRecompensa.obtenerRecompensas(userId, TipoRecompensa.ALIMENTO_GRATIS)
                .stream().findFirst().orElse(null);
    }
}
