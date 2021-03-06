package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.excepciones.TodosLosPerrosConPaseoActivoException;
import ar.edu.unlam.tallerweb1.modelo.Mascota;

import java.util.List;

public interface ServicioMascotas {
    List<Mascota> obtenerPerrosPorIdUsuario(Long idUsuario);

    Mascota obtenerMascotaPorId(Long idMascota);

    List<Mascota> obtenerPerrosQueNoEstenEnPaseo(Long userId) throws TodosLosPerrosConPaseoActivoException;
}
