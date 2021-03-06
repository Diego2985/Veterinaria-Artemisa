package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Articulo;

import java.util.List;

public interface RepositorioArticulo {

    List<Articulo> getArticulos();

    List<Articulo> buscarArticuloPorNombre(String busqueda);

    void update(Articulo item);

}
