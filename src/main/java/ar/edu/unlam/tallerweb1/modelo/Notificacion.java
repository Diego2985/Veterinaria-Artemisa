package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private String tipo;

    public Long getId() {
        return id;
    }

    public Notificacion setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public Notificacion setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Notificacion setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public Notificacion setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }
}
