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
    @Convert(converter = TipoRecompensaConverter.class)
    private TipoRecompensa tipo;
    private String fecha;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoRecompensa getTipo() {
        return tipo;
    }

    public void setTipo(TipoRecompensa tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
