package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.converter.Coordenadas;
import ar.edu.unlam.tallerweb1.excepciones.DatosCambiadosException;
import ar.edu.unlam.tallerweb1.excepciones.PaseadorConCantMaxDeMascotasException;
import ar.edu.unlam.tallerweb1.modelo.Paseador;
import ar.edu.unlam.tallerweb1.modelo.RegistroPaseo;
import ar.edu.unlam.tallerweb1.servicios.ServicioPaseador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ControladorPaseador {
    private ServicioPaseador servicioPaseador;

    @Autowired
    public ControladorPaseador(ServicioPaseador servicioPaseador) {
        this.servicioPaseador = servicioPaseador;
    }

    @RequestMapping(path = "/ver-paseadores", method = RequestMethod.POST)
    public ModelAndView obtenerPaseadoresCercanosA500mts(@RequestParam Double latitud, @RequestParam Double longitud) {
        try {
            ModelMap model = new ModelMap();
            Integer distancia = 500;
            List<Paseador> paseadores = servicioPaseador.obtenerListaDePaseadoresCercanos(latitud, longitud, distancia);
            model.put("paseadores", paseadores);
            model.put("latitud", latitud);
            model.put("longitud", longitud);
            return new ModelAndView("paseador-mapa", model);
        } catch (Exception e) {
            return new ModelAndView("error");
        }
    }

    // TODO: Chequear con la BD si el usuario tiene un registro activo, porque si cierra sesion se pierde la variable
    @RequestMapping("/paseador")
    public ModelAndView verPaginaDePaseador(HttpServletRequest request) {
        if (request.getSession().getAttribute("idRegistroPaseo") != null)
            return new ModelAndView("redirect:/paseador/seguimiento");
        return new ModelAndView("paseador-inicio");
    }

    @RequestMapping(path = "/contratar-paseador", method = RequestMethod.POST)
    public ModelAndView contratarAlPaseador(@RequestParam Long idPaseador, @RequestParam Double latitud, @RequestParam Double longitud, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        try {
            Paseador paseador = servicioPaseador.obtenerPaseador(idPaseador, true);
            Map<String, Coordenadas> coordenadas = obtenerCoordenadas(latitud, longitud, paseador);
            RegistroPaseo registro = servicioPaseador.crearRegistroDePaseo(paseador, (Long) request.getSession().getAttribute("userId"));
            request.getSession().setAttribute("idRegistroPaseo", registro.getId());
            model.put("idPaseador", idPaseador);
            model.put("paseador", paseador);
            model.put("registro", registro);
            return new ModelAndView("paseador-exitoso", model);
        } catch (PaseadorConCantMaxDeMascotasException e) {
            model.put("mensaje", "El paseador indicado no se encuentra disponible");
            return new ModelAndView("paseador-error");
        }
    }

    private Map<String, Coordenadas> obtenerCoordenadas(Double latitudUsuario, Double longitudUsuario, Paseador paseador) {
        Map<String, Coordenadas> coordenadas = new HashMap<>();
        coordenadas.put("usuario", new Coordenadas(latitudUsuario, longitudUsuario));
        coordenadas.put("paseador", new Coordenadas(paseador.getLatitud(), paseador.getLongitud()));
        return coordenadas;
    }

    @RequestMapping("/paseador/seguimiento")
    public ModelAndView consultarSeguimiento(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        if (request.getSession().getAttribute("idRegistroPaseo") == null)
            return new ModelAndView("redirect:/paseador");
        Long idRegistroPaseo = (Long) request.getSession().getAttribute("idRegistroPaseo");
        RegistroPaseo registro = servicioPaseador.obtenerRegistroDePaseo(idRegistroPaseo);
        Long minutosRestantes = ((registro.getHoraFinal().getTime() - new Date().getTime()) / 1000) / 60;
        model.put("registro", registro);
        model.put("tiempo", minutosRestantes);
        return new ModelAndView("seguimiento-paseo", model);
    }

    // TODO: El comienzo de paseo ahora está por default cuando se crea el registro. Debería tener el valor del comienzo del seguimiento
    // Opcion: crear nuevos campos de Hora de inicio real y hora de fin real
    // Opcion: Al actualizar el estado puede setear los Date now de comienzo real y del fin que se haga auto o seteado en Servicio
    @RequestMapping(path = "/comenzar-seguimiento", method = RequestMethod.POST)
    public ModelAndView realizarSeguimientoDePaseo(@RequestParam Long idRegistro, @RequestParam Long idPaseador, @RequestParam Long idUsuario) {
        ModelMap model = new ModelMap();
        try {
            servicioPaseador.actualizarRegistroDePaseo(idRegistro, idPaseador, idUsuario, 1);
            return new ModelAndView("redirect:/paseador/seguimiento");
        } catch (DatosCambiadosException e) {
            model.put("mensaje", e.getMessage());
            return new ModelAndView("paseador-error", model);
        }
    }

    @RequestMapping(path = "/paseador/finalizar-paseo", method = RequestMethod.POST)
    public ModelAndView finalizarPaseo(@RequestParam Long idRegistro, @RequestParam Long idPaseador, @RequestParam Long idUsuario, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        try {
            RegistroPaseo registro = servicioPaseador.actualizarRegistroDePaseo(idRegistro, idPaseador, idUsuario, 2);
            request.getSession().removeAttribute("idRegistroPaseo");
            model.put("registro", registro);
            return new ModelAndView("paseo-finalizado", model);
        } catch (DatosCambiadosException e) {
            model.put("mensaje", e.getMessage());
            return new ModelAndView("paseador-error", model);
        }
    }
}
