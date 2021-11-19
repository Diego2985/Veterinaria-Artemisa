package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMascotas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServicioMascotasImpl implements ServicioMascotas{
    private RepositorioMascotas repositorioMascotas;

    @Autowired
    public ServicioMascotasImpl(RepositorioMascotas repositorioMascotas) {
        this.repositorioMascotas = repositorioMascotas;
    }

    @Override
    public List<Mascota> obtenerPerrosPorIdUsuario(Long idUsuario) {
        return repositorioMascotas.obtenerPerrosPorIdUsuario(idUsuario);
    }

    @Override
    public Mascota obtenerMascotaPorId(Long idMascota) {
        return repositorioMascotas.obtenerMascotaPorId(idMascota);
    }
}
