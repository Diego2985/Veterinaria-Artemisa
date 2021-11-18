package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Articulo articulo;

    @ManyToOne
    private Usuario usuario;


    public Compra(long id, Articulo articulo, Usuario usuario) {
        this.id = id;
        this.articulo = articulo;
        this.usuario = usuario;
    }
    public Compra() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

