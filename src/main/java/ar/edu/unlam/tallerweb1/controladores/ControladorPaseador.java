package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.converter.Coordenadas;
import ar.edu.unlam.tallerweb1.converter.DatosTiempo;
import ar.edu.unlam.tallerweb1.converter.PaseoActivo;
import ar.edu.unlam.tallerweb1.converter.Ubicacion;
import ar.edu.unlam.tallerweb1.excepciones.*;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Paseador;
import ar.edu.unlam.tallerweb1.modelo.RegistroPaseo;
import ar.edu.unlam.tallerweb1.servicios.ServicioMascotas;
import ar.edu.unlam.tallerweb1.servicios.ServicioPaseador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ControladorPaseador {
    private ServicioPaseador servicioPaseador;
    private ServicioMascotas servicioMascotas;

    @Autowired
    public ControladorPaseador(ServicioPaseador servicioPaseador, ServicioMascotas servicioMascotas) {
        this.servicioPaseador = servicioPaseador;
        this.servicioMascotas = servicioMascotas;
    }

    @RequestMapping("/paseador")
    public ModelAndView listaDePaseos(HttpServletRequest request){
        ModelMap model = new ModelMap();
        try {
            Map<String, List<RegistroPaseo>> paseos = servicioPaseador.obtenerTodosLosRegistrosDePaseoDelUsuario((Long) request.getSession().getAttribute("userId"));
            Map<Long, DatosTiempo> datosPaseosAConfirmar = servicioPaseador.obtenerDatosDeTiempoYPosicion(paseos.get("proceso"),
                    (Double) request.getSession().getAttribute("latitudUsuario"),
                    (Double) request.getSession().getAttribute("longitudUsuario")
            );
            Map<Long, PaseoActivo> datosPaseosActivos = servicioPaseador.obtenerMasDatosDePaseosActivos(paseos.get("activos"));
            model.put("paseosActivos", datosPaseosActivos);
            model.put("paseos", paseos);
            model.put("paseosAConfirmar", datosPaseosAConfirmar);
            return new ModelAndView("paseos-confirmados", model);
        } catch (IOException e) {
            return new ModelAndView("error");
        }
    }

    @RequestMapping("/paseador/nuevo-paseo")
    public ModelAndView verPaginaDePaseador(HttpServletRequest request) {
        Long userId = null;
        ModelMap model = new ModelMap();
        try {
            userId = (Long) request.getSession().getAttribute("userId");
            servicioMascotas.obtenerPerrosQueNoEstenEnPaseo(userId);
            return new ModelAndView("paseador-inicio");
        } catch (TodosLosPerrosConPaseoActivoException e) {
            model.put("mensaje", e.getMessage());
            return new ModelAndView("paseador-error", model);
        }
    }

    @RequestMapping("/paseador/ver-paseadores")
    public ModelAndView redirigirAlIntentarAccederAlMapa(){
        return new ModelAndView("redirect:/paseador");
    }

    @RequestMapping(path = "/paseador/ver-paseadores", method = RequestMethod.POST)
    public ModelAndView obtenerPaseadoresCercanos(@RequestParam Double latitud, @RequestParam Double longitud, @RequestParam Integer distancia, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        try {
            Long userId = (Long) request.getSession().getAttribute("userId");
            List<Paseador> paseadores = servicioPaseador.obtenerListaDePaseadoresCercanos(latitud, longitud, distancia);
            Ubicacion ubicacion = servicioPaseador.obtenerDireccionDeUbicacionActual(latitud, longitud);
            List<Mascota> perros = servicioMascotas.obtenerPerrosQueNoEstenEnPaseo(userId);
            request.getSession().setAttribute("latitudUsuario", latitud);
            request.getSession().setAttribute("longitudUsuario", longitud);
            model.put("paseadores", paseadores);
            model.put("ubicacion", ubicacion);
            model.put("perros", perros);
            return new ModelAndView("paseador-mapa", model);
        } catch (Exception e) {
            return new ModelAndView("error");
        }
    }

    @RequestMapping(path = "/contratar-paseador", method = RequestMethod.POST)
    public ModelAndView contratarAlPaseador(@RequestParam Long idPaseador, @RequestParam Double latitud, @RequestParam Double longitud, @RequestParam Long perro, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        try {
            Mascota mascota = servicioMascotas.obtenerMascotaPorId(perro);
            Paseador paseador = servicioPaseador.obtenerPaseador(idPaseador, true);
            servicioPaseador.crearRegistroDePaseo(paseador, (Long) request.getSession().getAttribute("userId"), mascota);
            return new ModelAndView("redirect:/paseador");
        } catch (PaseadorConCantMaxDeMascotasException e) {
            model.put("mensaje", "El paseador indicado no se encuentra disponible");
            return new ModelAndView("paseador-error", model);
        }
    }

    @RequestMapping(path = "/paseador/comenzar-seguimiento", method = RequestMethod.POST)
    public ModelAndView realizarSeguimientoDePaseo(@RequestParam Long idRegistro, @RequestParam Long idPaseador, @RequestParam Long idUsuario, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        try {
            servicioPaseador.actualizarRegistroDePaseo(idRegistro, idPaseador, idUsuario, 1);
            return new ModelAndView("redirect:/paseador");
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
            servicioPaseador.cambiarEstadoDePaseoDeMascota(registro.getMascota());
            model.put("registro", registro);
            return new ModelAndView("paseo-finalizado", model);
        } catch (DatosCambiadosException e) {
            model.put("mensaje", e.getMessage());
            return new ModelAndView("paseador-error", model);
        }
    }
}
