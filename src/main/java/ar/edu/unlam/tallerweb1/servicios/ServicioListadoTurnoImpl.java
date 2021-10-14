package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioListadoTurno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@EnableTransactionManagement
public class ServicioListadoTurnoImpl implements ServicioListadoTurno {

    private RepositorioListadoTurno repositorioListadoTurno;

    @Autowired
    public ServicioListadoTurnoImpl(RepositorioListadoTurno repositorioListadoTurno) {
        this.repositorioListadoTurno = repositorioListadoTurno;
    }

    @Override
    public List<Turno> getListadoDeTurnos() {
        return repositorioListadoTurno.getListadoDeTurnos();
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
}
