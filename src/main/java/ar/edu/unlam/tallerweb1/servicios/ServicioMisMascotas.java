package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Mascota;

import java.util.List;

public interface ServicioMisMascotas {
    List<Mascota> obtenerMascotas(Long userId);
}
