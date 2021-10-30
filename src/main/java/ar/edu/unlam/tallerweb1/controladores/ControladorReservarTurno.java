package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.excepciones.FechaNoSeleccionada;
import ar.edu.unlam.tallerweb1.excepciones.HoraNoSeleccionada;
import ar.edu.unlam.tallerweb1.excepciones.ServicioNoSeleccionado;
import ar.edu.unlam.tallerweb1.excepciones.TurnoExistente;
import ar.edu.unlam.tallerweb1.modelo.DatosTurno;
import ar.edu.unlam.tallerweb1.modelo.Servicio;
import ar.edu.unlam.tallerweb1.servicios.ServicioReservarTurno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ControladorReservarTurno {

    private final ServicioReservarTurno servicioReservarTurno;

    @Autowired
    public ControladorReservarTurno(ServicioReservarTurno servicioReservarTurno) {
        this.servicioReservarTurno = servicioReservarTurno;
    }

    @RequestMapping(path="/reservar-turno")
    public ModelAndView irAReservarTurno() {
        ModelMap modelMap = new ModelMap();

        return new ModelAndView("reservar-turno", getDatosIniciales(modelMap));
    }

    @RequestMapping(path = "/reservar-turno", method = RequestMethod.POST)
    public ModelAndView reservar(@ModelAttribute("turno") DatosTurno datosTurno, HttpServletRequest request) {
        ModelMap modelMap = new ModelMap();

        try {
            Long userId = (Long) request.getSession().getAttribute("userId");
            servicioReservarTurno.reservar(datosTurno, userId);
        } catch (FechaNoSeleccionada e) {
            return reservaFallida(modelMap, "Fecha no seleccionada");
        } catch (HoraNoSeleccionada e) {
            return reservaFallida(modelMap, "Hora no seleccionada");
        } catch (ServicioNoSeleccionado e) {
            return reservaFallida(modelMap, "Servicio no seleccionado");
        } catch (TurnoExistente e) {
            return reservaFallida(modelMap, "Turno no disponible. Seleccione otro horario");
        }

        return reservaExitosa(modelMap);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    private ModelAndView reservaFallida(ModelMap model , String causa) {
        getDatosIniciales(model);
        model.put("error", causa);

        return new ModelAndView("reservar-turno", model);
    }

    private ModelAndView reservaExitosa(ModelMap model) {
        model.put("reservado", true);
        return new ModelAndView("redirect:/reserva-exitosa", model);
    }

    public ModelMap getDatosIniciales(ModelMap modelo) {
        List<Servicio> servicios = servicioReservarTurno.getServicios();
        List<String> horas = servicioReservarTurno.getHorasDisponibles();

        String fechaDesde = servicioReservarTurno.getFechaDesde();
        String fechaHasta = servicioReservarTurno.getFechaHasta();

        DatosTurno datosTurno = new DatosTurno()
                .setFechaDesde(fechaDesde)
                .setFechaHasta(fechaHasta)
                .setHoras(horas)
                .setHoraSeleccionada("")
                .setServicios(servicios)
                .setPrecio(0.0)
                .setServiciosSeleccionados(new ArrayList<>());

        modelo.put("datosTurno", datosTurno);

        return modelo;
    }
}
