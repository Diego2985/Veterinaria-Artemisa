package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface ServicioUsuarios {
    List<Usuario> getUsuarios(Long userId);
    Usuario getUsuarioPorId(Long userId);
}
