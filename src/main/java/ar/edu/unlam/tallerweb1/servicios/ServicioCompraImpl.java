package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Articulo;
import ar.edu.unlam.tallerweb1.modelo.Compra;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service @Transactional @EnableTransactionManagement
public class ServicioCompraImpl implements ServicioCompra{


    private final RepositorioCompra repositorioCompra;

    @Autowired
    public ServicioCompraImpl(RepositorioCompra repositorioCompra) {
        this.repositorioCompra = repositorioCompra;
    }

    @Override
    public Compra buscarCompra(Long id) {
        return repositorioCompra.buscarCompraPorId(id);
    }

    @Override
    public Articulo buscarArticulo(Long id) {
        return repositorioCompra.buscarArticuloPorId(id);
    }


}

