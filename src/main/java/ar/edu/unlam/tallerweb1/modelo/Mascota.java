package ar.edu.unlam.tallerweb1.modelo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
}
