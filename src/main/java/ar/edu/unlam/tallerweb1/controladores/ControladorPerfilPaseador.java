package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.RegistroPaseo;
import ar.edu.unlam.tallerweb1.servicios.ServicioPaseador;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ControladorPerfilPaseador {
    private ServicioPaseador servicioPaseador;

    @Autowired
    public ControladorPerfilPaseador(ServicioPaseador servicioPaseador) {
        this.servicioPaseador = servicioPaseador;
    }

    @RequestMapping("/paseador/paseos/pendientes")
    public ModelAndView obtenerPaseosPendientes(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        List<RegistroPaseo> pendientes = servicioPaseador.obtenerPaseosDeUnPaseador((Long) request.getSession().getAttribute("userId"));
        model.put("paseos", pendientes);
        return new ModelAndView("paseador-pendientes", model);
    }
}
