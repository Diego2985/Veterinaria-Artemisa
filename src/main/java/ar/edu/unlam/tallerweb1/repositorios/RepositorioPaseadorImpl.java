package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Paseador;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioPaseadorImpl implements RepositorioPaseador{
    @Override
    public List<Paseador> obtenerPaseadores() {
        return null;
    }
}
