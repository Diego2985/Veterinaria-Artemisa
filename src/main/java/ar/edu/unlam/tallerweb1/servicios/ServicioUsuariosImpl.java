package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@EnableTransactionManagement
public class ServicioUsuariosImpl implements ServicioUsuarios {

    private final RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioUsuariosImpl(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public List<Usuario> getUsuarios(Long userId) {
        return repositorioUsuario.getUsuarios(userId);
    }
}
