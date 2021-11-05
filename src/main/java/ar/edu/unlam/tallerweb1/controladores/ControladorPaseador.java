package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.converter.Coordenadas;
import ar.edu.unlam.tallerweb1.converter.DatosTiempo;
import ar.edu.unlam.tallerweb1.converter.Ubicacion;
import ar.edu.unlam.tallerweb1.excepciones.PaseadorConCantMaxDeMascotasException;
import ar.edu.unlam.tallerweb1.modelo.Paseador;
import ar.edu.unlam.tallerweb1.modelo.RegistroPaseo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
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
            Ubicacion ubicacion = servicioPaseador.obtenerDireccionDeUbicacionActual(latitud, longitud);
            model.put("paseadores", paseadores);
            model.put("ubicacion", ubicacion);
            return new ModelAndView("paseador-mapa", model);
        } catch (Exception e) {
            return new ModelAndView("error");
        }
    }

    @RequestMapping("/paseador")
    public ModelAndView verPaginaDePaseador() {
        return new ModelAndView("paseador-inicio");
    }

    @RequestMapping(path = "/contratar-paseador", method = RequestMethod.POST)
    public ModelAndView contratarAlPaseador(@RequestParam Long idPaseador, @RequestParam Double latitud, @RequestParam Double longitud, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        try {
            Paseador paseador = servicioPaseador.obtenerPaseador(idPaseador, true);
            Map<String, Coordenadas> coordenadas = obtenerCoordenadas(latitud, longitud, paseador);
            DatosTiempo distanciaYTiempo = servicioPaseador.obtenerDistanciaYTiempo(coordenadas.get("usuario"), coordenadas.get("paseador"));
            String obtenerImagen = servicioPaseador.obtenerImagenDeRutaDePaseadorAUsuario(coordenadas.get("usuario"), coordenadas.get("paseador"));
            RegistroPaseo registro = servicioPaseador.crearRegistroDePaseo(paseador, (Long) request.getSession().getAttribute("userId"));
            model.put("idPaseador", idPaseador);
            model.put("paseador", paseador);
            model.put("imagen", obtenerImagen);
            model.put("distanciaYTiempo", distanciaYTiempo);
            model.put("registro", registro);
            return new ModelAndView("paseador-exitoso", model);
        } catch (PaseadorConCantMaxDeMascotasException e) {
            return new ModelAndView("paseador-no-disponible");
        } catch (UnsupportedEncodingException e) {
            model.put("mensaje", "No se pudo obtener la imagen de la ruta");
            return new ModelAndView("paseador-exitoso", model);
        } catch (IOException e) {
            model.put("mensaje", "No se pudo obtener el tiempo de llegada ni la distancia");
            return new ModelAndView("paseador-exitoso", model);
        }
    }

    private Map<String, Coordenadas> obtenerCoordenadas(Double latitudUsuario, Double longitudUsuario, Paseador paseador){
        Map<String, Coordenadas> coordenadas = new HashMap<>();
        coordenadas.put("usuario", new Coordenadas(latitudUsuario, longitudUsuario));
        coordenadas.put("paseador", new Coordenadas(paseador.getLatitud(), paseador.getLongitud()));
        return coordenadas;
    }

    @RequestMapping(path = "/comenzar-seguimiento", method = RequestMethod.POST)
    public ModelAndView realizarSeguimientoDePaseo(@RequestParam DatosTiempo datosTiempo){
        ModelMap model = new ModelMap();
        model.put("datos", datosTiempo);
        return new ModelAndView("seguimiento-paseo", model);
    }
}
