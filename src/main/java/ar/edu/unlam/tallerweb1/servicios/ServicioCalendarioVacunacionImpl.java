package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Vacuna;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCalendarioVacunacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional
@EnableTransactionManagement
public class ServicioCalendarioVacunacionImpl implements ServicioCalendarioVacunacion {

    private RepositorioCalendarioVacunacion repositorioCalendarioVacunacion;

    @Autowired
    public ServicioCalendarioVacunacionImpl(RepositorioCalendarioVacunacion repositorioCalendarioVacunacion) {
        this.repositorioCalendarioVacunacion = repositorioCalendarioVacunacion;
    }

    @Override
    public List<Vacuna> getVacunas(Long userId) {
        List<Mascota> mascotas = repositorioCalendarioVacunacion.getMascotas(userId);
        List<Vacuna> vacunasDeGatos = repositorioCalendarioVacunacion.getVacunas("gato");
        List<Vacuna> vacunasDePerros = repositorioCalendarioVacunacion.getVacunas("perro");
        List<Vacuna> calendarioDeVacunas = new ArrayList<>();

        mascotas.forEach(item ->  {
                if (item.getTipo().equals("gato")) {
                    calendarioDeVacunas.addAll(getCalendarioDeVacunasSegunMascota(vacunasDeGatos, item));
                } else {
                    calendarioDeVacunas.addAll(getCalendarioDeVacunasSegunMascota(vacunasDePerros, item));
                }
        });

        return calendarioDeVacunas;
    }

    private List<Vacuna> getCalendarioDeVacunasSegunMascota(List<Vacuna> vacunas, Mascota item) {
        Calendar calendar = Calendar.getInstance();
        List<Vacuna> calendarioDeVacunas = new ArrayList<>();

        for (Vacuna vacuna : vacunas) {
            calendar.setTime(item.getFechaNacimiento());
            calendar.add(Calendar.MONTH, vacuna.getMeses());
            Vacuna vacunaCalendario = new Vacuna()
                    .setTitulo(vacuna.getTitulo().concat(" - ").concat(item.getNombre()))
                    .setFecha(calendar.toInstant().toString());
            calendarioDeVacunas.add(vacunaCalendario);
        }

        return calendarioDeVacunas;
    }
}
