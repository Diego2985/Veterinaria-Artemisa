package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMisMascotas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServicioMisMascotasImpl implements ServicioMisMascotas {

    private final RepositorioMisMascotas repositorioMisMascotas;

    @Autowired
    public ServicioMisMascotasImpl(RepositorioMisMascotas repositorioMisMascotas) {
        this.repositorioMisMascotas = repositorioMisMascotas;
    }

    @Override
    public List<Mascota> obtenerMascotas(Long userId) {
        return repositorioMisMascotas.obtenerMascotas(userId);
    }
}
