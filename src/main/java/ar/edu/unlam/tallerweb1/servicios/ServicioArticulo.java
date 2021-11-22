package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Articulo;

import java.util.List;

public interface ServicioArticulo {

    List<Articulo> getArticulos();

    List<Articulo> buscarArticulosPorNombre(String busqueda);

    void update(Long idArticulo);
}
