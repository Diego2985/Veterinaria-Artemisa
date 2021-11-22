package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
@EnableTransactionManagement
public class ServicioNotificacionImpl implements ServicioNotificacion {

    private final RepositorioNotificacion repositorioNotificacion;
    private final static int MIN_DIAS_PROXIMO_TURNO = 3;

    @Autowired
    public ServicioNotificacionImpl(RepositorioNotificacion repositorioNotificacion) {
        this.repositorioNotificacion = repositorioNotificacion;
    }

    @Override
    public List<Notificacion> getNotificaciones(Long userId) {
        List<Notificacion> notificacionesAMostrar = new ArrayList<>();
        List<Notificacion> notificaciones = repositorioNotificacion.getNotificaciones(userId);
        notificaciones.forEach(item -> {
            manejadorDeNotifiaciones(item, notificacionesAMostrar);
        });

        return notificacionesAMostrar;
    }

    @Override
    public void generaTurnoNotificacion(Turno turno) {
        repositorioNotificacion.generaTurnoNotificacion(turno);
    }

    @Override
    public void update(Long idNotificacion, Long userId) {
        List<Notificacion> notificaciones = repositorioNotificacion.getNotificaciones(userId);
        notificaciones.forEach(item -> {
            if (item.getId().equals(idNotificacion)) {
                item.setLeida(true);
                repositorioNotificacion.update(item);
            }
        });
    }

    private void manejadorDeNotifiaciones(Notificacion notificacion, List<Notificacion> notificacionesAMostrar) {
        switch (notificacion.getTipo()) {
            case PROXIMO_TURNO: {
                long dias = getDifferenceDays(getFechaFormateada(notificacion.getFecha()), new Date());
                if (dias < MIN_DIAS_PROXIMO_TURNO && !notificacion.getLeida()) {
                    notificacionesAMostrar.add(notificacion);
                }
                break;
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

    public long getDifferenceDays(Date d1, Date d2) {
        long diff = d1.getTime() - d2.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
