package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vacuna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String fecha;
    private String hora;
    private String tipo;
    private Integer meses;

    public Long getId() {
        return id;
    }

    public Vacuna setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public Vacuna setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String getFecha() {
        return fecha;
    }

    public Vacuna setFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }

    public String getHora() {
        return hora;
    }

    public Vacuna setHora(String hora) {
        this.hora = hora;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public Vacuna setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public Integer getMeses() {
        return meses;
    }

    public Vacuna setMeses(Integer meses) {
        this.meses = meses;
        return this;
    }
}
