package ar.edu.unlam.tallerweb1.modelo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Embeddable
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    private Double precio;
    private String hora;

    public Turno(Date fecha, Double precio) {
        this.fecha = fecha;
        this.precio = precio;
    }

    public Turno() {}

    public Turno(DatosTurno datosTurno) {
        this.fecha = datosTurno.getFecha();
        this.hora = datosTurno.getHoraSeleccionada();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getPrecio() {
        return precio;
    }

    public String getHora() {
        return hora;
    }
}
