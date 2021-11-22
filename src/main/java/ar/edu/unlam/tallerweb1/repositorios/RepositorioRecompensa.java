package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Recompensa;
import ar.edu.unlam.tallerweb1.modelo.TipoRecompensa;

import java.util.List;

public interface RepositorioRecompensa {
    List<Recompensa> obtenerRecompensas(Long userId, TipoRecompensa tipoRecompensa);

    void guardarRecompensa(Recompensa recompensa);
}
