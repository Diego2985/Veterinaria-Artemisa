package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.DatosCompra;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorCompra {

    @RequestMapping(path = "/compra-articulo", method = RequestMethod.GET)
    public ModelAndView irAComprar() {
        ModelMap modelMap = new ModelMap();
        modelMap.put("datosCompra", new DatosCompra());
        return new ModelAndView("compra-articulo", modelMap);
    }

    @RequestMapping(path = "/confirmar-compra", method = RequestMethod.GET)
    public ModelAndView confirmarCompra(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        return new ModelAndView("compra-exitosa");
    }

    @RequestMapping(path = "/compra-articulo", method = RequestMethod.POST)
    public ModelAndView datosTarjeta(@ModelAttribute("datosCompra") DatosCompra datosCompra) {
        ModelMap modelo = new ModelMap();
        if (datosCompra.getAnio().equals("2021")) {
            modelo.put("exitoso", "El pago se realizo exitosamente");
            return new ModelAndView("compra-exitosa", modelo);
        }
        modelo.put("error", "La tarjeta esta vencida");
        return new ModelAndView("compra-fallida", modelo);
    }



 //  @RequestMapping(path = "/confirmar-articulo", method = RequestMethod.GET)
 //  public ModelAndView aprobarTarjeta(DatosCompra datosCompra) {
 //  return new ModelAndView("compra-articulo");
//  }
}

