package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Articulo;
import ar.edu.unlam.tallerweb1.modelo.Compra;

public interface RepositorioCompra {

    Compra buscarCompraPorId(Long id);

    Articulo buscarArticuloPorId(Long id);
}
