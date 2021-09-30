package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioListadoTurno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@EnableTransactionManagement
public class ServicioListadoTurnoImpl implements ServicioListadoTurno {

    private RepositorioListadoTurno repositorioListadoTurno;

    @Autowired
    public ServicioListadoTurnoImpl(RepositorioListadoTurno repositorioListadoTurno) {
        this.repositorioListadoTurno = repositorioListadoTurno;
    }

    @Override
    public List<Turno> getListadoDeTurnos() {
        return repositorioListadoTurno.getListadoDeTurnos();
    }
}
