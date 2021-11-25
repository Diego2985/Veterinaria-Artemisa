package ar.edu.unlam.tallerweb1.modelo;

public class Compra {

    private Usuario usuario;
    private Articulo articulo;



    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
}

// ver que este en castellano los textos de la vista - listo
// el año que empiece de 2021 en adelante - test
// poner el id del articulo
// revisar que los campos no este vacios, y que tenga datosCompra (para la tarjeta este completo ) - listo, falta test
// vista compra-exitosa.
