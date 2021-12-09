package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.converter.PaseoActivo;
import ar.edu.unlam.tallerweb1.modelo.RegistroPaseo;
import ar.edu.unlam.tallerweb1.servicios.ServicioPaseador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ControladorPerfilPaseador {
    private ServicioPaseador servicioPaseador;

    @Autowired
    public ControladorPerfilPaseador(ServicioPaseador servicioPaseador) {
        this.servicioPaseador = servicioPaseador;
    }

    @RequestMapping("/paseador/paseos/pendientes")
    public ModelAndView obtenerPaseosPendientes(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        try {
            if (request.getSession().getAttribute("userId") == null || !request.getSession().getAttribute("userRol").equals("2"))
                return new ModelAndView("redirect:/");
            List<RegistroPaseo> paseos = servicioPaseador.obtenerPaseosDeUnPaseador((Long) request.getSession().getAttribute("userId"), 0);
            HashMap<Long, String> imagenesDeRutas = servicioPaseador.obtenerImagenesDeRutaADomicilios(paseos);
            model.put("imagenes", imagenesDeRutas);
            model.put("paseos", paseos);
            return new ModelAndView("paseador-lista-pendientes", model);
        } catch (UnsupportedEncodingException e) {
            model.put("mensaje", e.getMessage());
            return new ModelAndView("paseador-error", model);
        }
    }

    @RequestMapping("/paseador/paseos/activos")
    public ModelAndView obtenerPaseosActivos(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        try {
            if (request.getSession().getAttribute("userId") == null || !request.getSession().getAttribute("userRol").equals("2"))
                return new ModelAndView("redirect:/");
            List<RegistroPaseo> paseos = servicioPaseador.obtenerPaseosDeUnPaseador((Long) request.getSession().getAttribute("userId"), 1);
            Map<Long, PaseoActivo> tiempos = servicioPaseador.obtenerMasDatosDePaseosActivos(paseos);
            model.put("paseos", paseos);
            model.put("tiempos", tiempos);
            return new ModelAndView("paseador-lista-activos", model);
        } catch (IOException e) {
            model.put("mensaje", e.getMessage());
            return new ModelAndView("paseador-error", model);
        }
    }

    @RequestMapping("/paseador/paseos/finalizados")
    public ModelAndView obtenerPaseosFinalizados(HttpServletRequest request) {
        if (request.getSession().getAttribute("userId") == null || !request.getSession().getAttribute("userRol").equals("2"))
            return new ModelAndView("redirect:/");
        ModelMap model = new ModelMap();
        List<RegistroPaseo> paseos = servicioPaseador.obtenerPaseosDeUnPaseador((Long) request.getSession().getAttribute("userId"), 2);
        model.put("paseos", paseos);
        return new ModelAndView("paseador-lista-finalizados", model);
    }
}
