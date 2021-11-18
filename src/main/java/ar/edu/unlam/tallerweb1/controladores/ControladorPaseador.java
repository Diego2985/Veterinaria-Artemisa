package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.converter.Coordenadas;
import ar.edu.unlam.tallerweb1.converter.DatosTiempo;
import ar.edu.unlam.tallerweb1.converter.Ubicacion;
import ar.edu.unlam.tallerweb1.excepciones.*;
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
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ControladorPaseador {
    private ServicioPaseador servicioPaseador;

    @Autowired
    public ControladorPaseador(ServicioPaseador servicioPaseador) {
        this.servicioPaseador = servicioPaseador;
    }

    @RequestMapping("/ver-paseadores")
    public ModelAndView redirigirAlIntentarAccederAlMapa(){
        return new ModelAndView("redirect:/paseador");
    }

    @RequestMapping(path = "/ver-paseadores", method = RequestMethod.POST)
    public ModelAndView obtenerPaseadoresCercanos(@RequestParam Double latitud, @RequestParam Double longitud, @RequestParam Integer distancia, HttpServletRequest request) {
        try {
            ModelMap model = new ModelMap();
            List<Paseador> paseadores = servicioPaseador.obtenerListaDePaseadoresCercanos(latitud, longitud, distancia);
            Ubicacion ubicacion = servicioPaseador.obtenerDireccionDeUbicacionActual(latitud, longitud);
            model.put("paseadores", paseadores);
            model.put("ubicacion", ubicacion);
            return new ModelAndView("paseador-mapa", model);
        } catch (Exception e) {
            return new ModelAndView("error");
        }
    }

    @RequestMapping("/paseador")
    public ModelAndView verPaginaDePaseador(HttpServletRequest request) {
        Long userId = null;
        try {
            userId = (Long) request.getSession().getAttribute("userId");
            Integer estadoPaseo = (Integer) request.getSession().getAttribute("estadoPaseo");
            servicioPaseador.validarEstadoEnSesion(estadoPaseo);
            servicioPaseador.verificarSiUnUsuarioTieneUnPaseoActivo(userId);
            return new ModelAndView("paseador-inicio");
        } catch (PaseoNoIniciadoException e) {
            setearVariablesDeSesionSiNoExisten(request, userId);
            return new ModelAndView("redirect:/paseador/en-proceso");
        } catch (PaseoIniciadoException e) {
            setearVariablesDeSesionSiNoExisten(request, userId);
            return new ModelAndView("redirect:/paseador/seguimiento");
        }
    }

    private void setearVariablesDeSesionSiNoExisten(HttpServletRequest request, Long userId) {
        if(request.getSession().getAttribute("idRegistroPaseo") == null || request.getSession().getAttribute("estadoPaseo") == null){
            RegistroPaseo registroPaseo = servicioPaseador.obtenerRegistroDePaseoActivoOEnProceso(userId);
            request.getSession().setAttribute("idRegistroPaseo", registroPaseo.getId());
            request.getSession().setAttribute("estadoPaseo", registroPaseo.getEstado());
        }
    }

    @RequestMapping("/paseador/en-proceso")
    public ModelAndView paseadorEnProcesoDeLlegada(HttpServletRequest request){
        ModelMap model = new ModelMap();
        try {
            if(servicioPaseador.chequearAccesoCorrecto(request, 0)) {
                return new ModelAndView("redirect:/paseador");
            }
            Long idRegistro = (Long) request.getSession().getAttribute("idRegistroPaseo");
            RegistroPaseo registro = servicioPaseador.obtenerRegistroDePaseo(idRegistro);
            Double latitud = (Double) request.getSession().getAttribute("latitud");
            Double longitud = (Double) request.getSession().getAttribute("longitud");
            Map<String, Coordenadas> coordenadas = servicioPaseador.obtenerCoordenadas(latitud, longitud, registro.getPaseador());
            request.getSession().removeAttribute("latitud");
            request.getSession().removeAttribute("longitud");
            DatosTiempo distanciaYTiempo = servicioPaseador.obtenerDistanciaYTiempo(coordenadas.get("usuario"), coordenadas.get("paseador"));
            String obtenerImagen = servicioPaseador.obtenerImagenDeRutaDePaseadorAUsuario(coordenadas.get("usuario"), coordenadas.get("paseador"));
            model.put("idPaseador", registro.getPaseador().getId());
            model.put("paseador", registro.getPaseador());
            model.put("registro", registro);
            model.put("distanciaYTiempo", distanciaYTiempo);
            model.put("imagen", obtenerImagen);
            return new ModelAndView("paseador-exitoso", model);
        } catch (UnsupportedEncodingException e) {
            model.put("mensaje", "No se pudo obtener la imagen de la ruta");
            return new ModelAndView("paseador-error", model);
        } catch (IOException e) {
            model.put("mensaje", "No se pudo obtener el tiempo de llegada ni la distancia");
            return new ModelAndView("paseador-error", model);
        }
    }

    @RequestMapping(path = "/contratar-paseador", method = RequestMethod.POST)
    public ModelAndView contratarAlPaseador(@RequestParam Long idPaseador, @RequestParam Double latitud, @RequestParam Double longitud, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        try {
            Paseador paseador = servicioPaseador.obtenerPaseador(idPaseador, true);
            RegistroPaseo registro = servicioPaseador.crearRegistroDePaseo(paseador, (Long) request.getSession().getAttribute("userId"));
            request.getSession().setAttribute("idRegistroPaseo", registro.getId());
            request.getSession().setAttribute("estadoPaseo", registro.getEstado());
            request.getSession().setAttribute("latitud", latitud);
            request.getSession().setAttribute("longitud", longitud);
            return new ModelAndView("redirect:paseador/en-proceso");
        } catch (PaseadorConCantMaxDeMascotasException e) {
            model.put("mensaje", "El paseador indicado no se encuentra disponible");
            return new ModelAndView("paseador-error", model);
        }
    }

    @RequestMapping("/paseador/seguimiento")
    public ModelAndView consultarSeguimiento(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        try {
            if(servicioPaseador.chequearAccesoCorrecto(request, 1)) {
                return new ModelAndView("redirect:/paseador");
            }
            Long idRegistroPaseo = (Long) request.getSession().getAttribute("idRegistroPaseo");
            RegistroPaseo registro = servicioPaseador.obtenerRegistroDePaseo(idRegistroPaseo);
            Long minutosRestantes = ((registro.getHoraFinal().getTime() - new Date().getTime()) / 1000) / 60;
            String imagenPosicionDelPaseador = servicioPaseador.obtenerImagenDePosicionDelPaseador(registro.getPaseador().getId());
            model.put("registro", registro);
            model.put("tiempo", minutosRestantes);
            model.put("imagen", imagenPosicionDelPaseador);
            return new ModelAndView("seguimiento-paseo", model);
        } catch (UnsupportedEncodingException e) {
            model.put("mensaje", "Error en la obtención de la imagen. Intente más tarde");
            return new ModelAndView("paseador-error", model);
        }
    }

    @RequestMapping(path = "/paseador/comenzar-seguimiento", method = RequestMethod.POST)
    public ModelAndView realizarSeguimientoDePaseo(@RequestParam Long idRegistro, @RequestParam Long idPaseador, @RequestParam Long idUsuario, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        try {
            servicioPaseador.actualizarRegistroDePaseo(idRegistro, idPaseador, idUsuario, 1);
            request.getSession().setAttribute("estadoPaseo", 1);
            return new ModelAndView("redirect:seguimiento");
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
            request.getSession().removeAttribute("estadoPaseo");
            model.put("registro", registro);
            return new ModelAndView("paseo-finalizado", model);
        } catch (DatosCambiadosException e) {
            model.put("mensaje", e.getMessage());
            return new ModelAndView("paseador-error", model);
        }
    }
}
