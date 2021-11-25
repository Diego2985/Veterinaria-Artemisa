package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Recompensa;
import ar.edu.unlam.tallerweb1.modelo.TipoRecompensa;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioRecompensa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    public void generarRecompensa(Long userId, TipoRecompensa tipoRecompensa, Long cantidad) {
        Optional<Recompensa> recompensaYaExistente = obtenerRecompensas(userId, tipoRecompensa)
                .stream().findAny();
        if (recompensaYaExistente.isEmpty()) {
            switch (tipoRecompensa) {
                case TURNO_GRATIS: {
                    if (cantidad > 5) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.DATE, 7);
                        Recompensa recompensa = new Recompensa()
                                .setTitulo("¡Turno gratis!")
                                .setDescripcion("Por haber reservado más de 5 turnos te regalamos uno gratis.")
                                .setCaducidad("Tenés una semana para reclamar tur premio. ¡No lo olvides!")
                                .setFecha(calendar.getTime().toInstant().toString())
                                .setTipo(tipoRecompensa)
                                .setUserId(userId);
                        repositorioRecompensa.guardarRecompensa(recompensa);
                    }
                    break;
                }
                case ALIMENTO_GRATIS: {
                    if (cantidad > 2) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.DATE, 7);
                        Recompensa recompensa = new Recompensa()
                                .setTitulo("¡Alimento gratis!")
                                .setDescripcion("Por tener registrado más de 2 mascotas te regalamos un alimento gratis.")
                                .setCaducidad("Tenés una semana para reclamar tur premio. ¡No lo olvides!")
                                .setFecha(calendar.getTime().toInstant().toString())
                                .setTipo(tipoRecompensa)
                                .setUserId(userId);
                        repositorioRecompensa.guardarRecompensa(recompensa);
                    }
                    break;
                }
            }
        }
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
