package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private float precio;
    @Column(nullable = false)
    private boolean mostrado = false;

    public boolean isMostrado() {
        return mostrado;
    }

    public void setMostrado(boolean mostrado) {
        this.mostrado = mostrado;
    }

    public Boolean getEsNuevo() {
        return esNuevo;
    }

    public void setEsNuevo(Boolean esNuevo) {
        this.esNuevo = esNuevo;
    }

    private Boolean esNuevo;

    public Articulo() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTituloArticulo() {
        return titulo;
    }

    public void setTituloArticulo(String tituloArticulo) {
        this.titulo = tituloArticulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
