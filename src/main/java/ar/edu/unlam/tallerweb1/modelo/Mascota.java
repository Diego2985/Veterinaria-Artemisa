package ar.edu.unlam.tallerweb1.modelo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Entity
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    private String tipo;
    private Long userId;
    private Boolean paseoActivo = false;
    @Transient
    private long edad;

    public Long getId() {
        return id;
    }

    public Mascota setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Mascota setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Mascota setFechaNacimiento(Date fecha) {
        this.fechaNacimiento = fecha;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public Mascota setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Mascota setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Boolean getPaseoActivo() {
        return paseoActivo;
    }

    public void setPaseoActivo(Boolean paseoActivo) {
        this.paseoActivo = paseoActivo;
    }

    public long getEdad() {
        YearMonth from = YearMonth.from(
                getFechaNacimiento()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
        );

        YearMonth to = YearMonth.from
                (new Date()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
        );

        edad = ChronoUnit.MONTHS.between(from, to);
        return edad;
    }

    public Mascota setEdad(long edad) {
        this.edad = edad;
        return this;
    }
}
