package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Recompensa;
import ar.edu.unlam.tallerweb1.modelo.TipoRecompensa;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioRecompensa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@EnableTransactionManagement
public class ServicioRecompensaImpl implements ServicioRecompensa {

    private final RepositorioRecompensa repositorioRecompensa;

    @Autowired
    public ServicioRecompensaImpl(RepositorioRecompensa repositorioRecompensa) {
        this.repositorioRecompensa = repositorioRecompensa;
    }

    @Override
    public List<Recompensa> obtenerRecompensas(Long userId, TipoRecompensa tipoRecompensa) {
        List<Recompensa> recompensas = repositorioRecompensa.obtenerRecompensas(userId, tipoRecompensa);
        return recompensas.stream()
                .filter(item -> new Date().before(getFechaFormateada(item.getFecha())))
                .collect(Collectors.toList());
    }

    @Override
    public void generarRecompensa() {

    }

    public Date getFechaFormateada(String fecha) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(fecha);
        } catch (Exception e) {
            return new Date();
        }
    }
}
