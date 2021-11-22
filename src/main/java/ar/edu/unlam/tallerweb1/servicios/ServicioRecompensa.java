package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Recompensa;
import ar.edu.unlam.tallerweb1.modelo.TipoRecompensa;

import java.util.List;

public interface ServicioRecompensa {
    List<Recompensa> obtenerRecompensas(Long userId, TipoRecompensa tipoRecompensa);

    void generarRecompensa();
}
