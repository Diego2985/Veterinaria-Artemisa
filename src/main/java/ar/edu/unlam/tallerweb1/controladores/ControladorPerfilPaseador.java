package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.RegistroPaseo;
import ar.edu.unlam.tallerweb1.servicios.ServicioPaseador;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorPerfilPaseador {
    private ServicioPaseador servicioPaseador;

    @Autowired
    public ControladorPerfilPaseador(ServicioPaseador servicioPaseador) {
        this.servicioPaseador = servicioPaseador;
    }

    @RequestMapping("/paseador/paseos/pendientes")
    public ModelAndView obtenerPaseosPendientes(HttpServletRequest request) {
        if (request.getSession().getAttribute("userId") == null || request.getSession().getAttribute("userRol") != "2")
            return new ModelAndView("redirect:/");
        ModelMap model = new ModelMap();
        List<RegistroPaseo> paseos = servicioPaseador.obtenerPaseosDeUnPaseador((Long) request.getSession().getAttribute("userId"), 0);
        model.put("paseos", paseos);
        model.put("titulo", "Paseos pendientes");
        return new ModelAndView("perfil-paseador-lista", model);
    }

    @RequestMapping("/paseador/paseos/activos")
    public ModelAndView obtenerPaseosActivos(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        List<RegistroPaseo> paseos = servicioPaseador.obtenerPaseosDeUnPaseador((Long) request.getSession().getAttribute("userId"), 1);
        model.put("paseos", paseos);
        model.put("titulo", "Paseos activos");
        return new ModelAndView("perfil-paseador-lista", model);
    }

    @RequestMapping("/paseador/paseos/finalizados")
    public ModelAndView obtenerPaseosFinalizados(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        List<RegistroPaseo> paseos = servicioPaseador.obtenerPaseosDeUnPaseador((Long) request.getSession().getAttribute("userId"), 2);
        model.put("paseos", paseos);
        model.put("titulo", "Paseos finalizados");
        return new ModelAndView("perfil-paseador-lista", model);
    }
}
