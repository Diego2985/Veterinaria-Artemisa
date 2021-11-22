package ar.edu.unlam.tallerweb1.modelo;

import ar.edu.unlam.tallerweb1.converter.TipoNotificacionConversor;
import javax.persistence.*;

@Entity
@Embeddable
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    @Convert(converter = TipoNotificacionConversor.class)
    private TipoNotificacion tipo;
    private String fecha;
    private String action;
    private Long userId;
    private Boolean leida;

    public Notificacion() {}

    public Notificacion(Turno turno) {
        titulo = "Tenes un turno pronto";
        descripcion = turno.getServiciosSeleccionados();
        tipo = TipoNotificacion.PROXIMO_TURNO;
        fecha = turno.getFecha().toInstant().toString();
        action = "listado-turnos";
        userId = turno.getUserId();
        leida = false;
    }

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

    public TipoNotificacion getTipo() {
        return tipo;
    }

    public Notificacion setTipo(TipoNotificacion tipo) {
        this.tipo = tipo;
        return this;
    }

    public String getFecha() {
        return fecha;
    }

    public Notificacion setFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }

    public String getAction() {
        return action;
    }

    public Notificacion setAction(String action) {
        this.action = action;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Notificacion setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Notificacion setLeida(Boolean leida) {
        this.leida = leida;
        return this;
    }

    public Boolean getLeida() {
        return leida;
    }
}
