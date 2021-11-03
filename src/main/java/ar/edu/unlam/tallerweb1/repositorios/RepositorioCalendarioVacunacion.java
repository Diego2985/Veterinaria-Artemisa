package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Vacuna;

import java.util.List;

public interface RepositorioCalendarioVacunacion {

    List<Vacuna> getVacunas(String tipo);

    List<Mascota> getMascotas(Long userId);

}
