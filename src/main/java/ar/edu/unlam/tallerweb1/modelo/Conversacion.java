package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.List;

@Entity
public class Conversacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OutputMessage> mensajes;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Usuario emisor;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Usuario receptor;

    public Conversacion(List<OutputMessage> mensajes, Usuario emisor, Usuario receptor) {
        this.mensajes = mensajes;
        this.emisor = emisor;
        this.receptor = receptor;
    }

    public Conversacion() {

    }

    public Long getId() {
        return id;
    }

    public Conversacion setId(Long id) {
        this.id = id;
        return this;
    }

    public List<OutputMessage> getMensajes() {
        return mensajes;
    }

    public Conversacion setMensajes(List<OutputMessage> mensajes) {
        this.mensajes = mensajes;
        return this;
    }

    public Usuario getEmisor() {
        return emisor;
    }

    public Conversacion setEmisor(Usuario emisor) {
        this.emisor = emisor;
        return this;
    }

    public Usuario getReceptor() {
        return receptor;
    }

    public Conversacion setReceptor(Usuario receptor) {
        this.receptor = receptor;
        return this;
    }
}
