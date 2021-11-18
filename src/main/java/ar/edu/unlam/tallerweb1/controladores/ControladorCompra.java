package ar.edu.unlam.tallerweb1.controladores;


import ar.edu.unlam.tallerweb1.modelo.Articulo;
import ar.edu.unlam.tallerweb1.modelo.Compra;
import ar.edu.unlam.tallerweb1.modelo.DatosPagos;
import ar.edu.unlam.tallerweb1.servicios.ServicioCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorCompra {

    @Autowired
    private ServicioCompra servicioCompra;
    private Compra Compra;
    private Articulo articulo;

    @Autowired
    public ControladorCompra(ServicioCompra servicioCompra){ this.servicioCompra = servicioCompra;}

    @RequestMapping(path = "/carrito/{id}", method = RequestMethod.GET)
    public ModelAndView irAVerLaCompra(@PathVariable Long id) {
        ModelMap model = new ModelMap();
        Compra = servicioCompra.buscarCompra(id);
        articulo = servicioCompra.buscarArticulo(id);
        model.put("id", id);

        model.put("articulo", articulo);
        return new ModelAndView("carrito", model);
    }

    @RequestMapping(path = "/metodo-de-pago", method = RequestMethod.GET)
    public ModelAndView irAComprar(){
        return new ModelAndView("login");
    }
}

