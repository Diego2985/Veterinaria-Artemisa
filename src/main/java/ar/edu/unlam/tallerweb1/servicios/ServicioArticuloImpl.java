package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Articulo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioArticulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@EnableTransactionManagement
public class ServicioArticuloImpl implements ServicioArticulo {

    private final RepositorioArticulo repositorioArticulo;

    @Autowired
    public ServicioArticuloImpl(RepositorioArticulo repositorioArticulo) {
        this.repositorioArticulo = repositorioArticulo;
    }

    @Override
    public List<Articulo> getArticulos() {
        return repositorioArticulo.getArticulos();
    }
}
