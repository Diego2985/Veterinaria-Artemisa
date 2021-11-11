package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Vacuna;

import java.util.List;

public interface ServicioCalendarioVacunacion {

    List<Vacuna> getVacunas(Long userId);
}
