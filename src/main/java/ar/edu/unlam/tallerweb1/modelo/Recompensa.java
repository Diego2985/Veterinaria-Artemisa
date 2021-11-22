package ar.edu.unlam.tallerweb1.modelo;

import ar.edu.unlam.tallerweb1.converter.TipoRecompensaConverter;

import javax.persistence.*;

@Entity
@Embeddable
public class Recompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private String caducidad;
    @Convert(converter = TipoRecompensaConverter.class)
    private TipoRecompensa tipo;
    private String fecha;
    private Long userId;

    public String getCaducidad() {
        return caducidad;
    }

    public Recompensa setCaducidad(String caducidad) {
        this.caducidad = caducidad;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Recompensa setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public Recompensa setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Recompensa setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public TipoRecompensa getTipo() {
        return tipo;
    }

    public Recompensa setTipo(TipoRecompensa tipo) {
        this.tipo = tipo;
        return this;
    }

    public String getFecha() {
        return fecha;
    }

    public Recompensa setFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Recompensa setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
