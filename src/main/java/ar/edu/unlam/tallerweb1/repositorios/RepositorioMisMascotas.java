package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Mascota;

import java.util.List;

public interface RepositorioMisMascotas {
    List<Mascota> obtenerMascotas(Long userId);
}
