package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.excepciones.FechaNoSeleccionada;
import ar.edu.unlam.tallerweb1.excepciones.HoraNoSeleccionada;
import ar.edu.unlam.tallerweb1.excepciones.ServicioNoSeleccionado;
import ar.edu.unlam.tallerweb1.excepciones.TurnoExistente;
import ar.edu.unlam.tallerweb1.modelo.DatosTurno;
import ar.edu.unlam.tallerweb1.modelo.Servicio;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioReservarTurno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
@EnableTransactionManagement
public class ServicioReservarTurnoImpl implements ServicioReservarTurno {

    private RepositorioReservarTurno repositorioReservarTurno;

    @Autowired
    public ServicioReservarTurnoImpl(RepositorioReservarTurno repositorioReservarTurno) {
        this.repositorioReservarTurno = repositorioReservarTurno;
    }

    @Override
    public Turno reservar(DatosTurno datosTurno, Long userId) {
        if (datosTurno.getFecha() == null) {
            throw new FechaNoSeleccionada();
        }

        if (datosTurno.getHoraSeleccionada().isEmpty()) {
            throw new HoraNoSeleccionada();
        }

        if (datosTurno.getServiciosSeleccionados() == null) {
            throw new ServicioNoSeleccionado();
        }

        Turno turnoExistente = repositorioReservarTurno.consultarTurno(
                datosTurno.getFecha(),
                datosTurno.getHoraSeleccionada()
        );

        if (turnoExistente != null) {
            throw new TurnoExistente();
        }

        Turno turno = new Turno(datosTurno, userId);
        turno.setServicios(getServiciosSeleccionados(datosTurno.getServiciosSeleccionados()));
        repositorioReservarTurno.reservar(turno);

        return turno;
    }

    @Override
    public List<Servicio> getServicios() {
        return repositorioReservarTurno.getServicios();
    }

    @Override
    public List<String> getHorasDisponibles() {
        return repositorioReservarTurno.getHorasDisponibles();
    }

    @Override
    public String getFechaDesde() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        calendar.add(Calendar.DATE, 1);

        return dateFormat.format(calendar.getTime());
    }

    @Override
    public String getFechaHasta() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        calendar.add(Calendar.DATE, 15);

        return dateFormat.format(calendar.getTime());
    }

    public List<Servicio> getServiciosSeleccionados(List<String> serviciosSeleccionados) {
        List<Servicio> servicios = new ArrayList<>();

        for (String idsServicio : serviciosSeleccionados) {
            Optional<Servicio> itemBuscado = repositorioReservarTurno.findServicioById(idsServicio);
            itemBuscado.ifPresent(servicios::add);
        }

        return servicios;
    }
}
