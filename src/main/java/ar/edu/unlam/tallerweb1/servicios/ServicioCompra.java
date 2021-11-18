package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Articulo;
import ar.edu.unlam.tallerweb1.modelo.Compra;

import java.util.Date;


public interface ServicioCompra {

    Compra buscarCompra(Long id);
    Articulo buscarArticulo(Long id);


}
