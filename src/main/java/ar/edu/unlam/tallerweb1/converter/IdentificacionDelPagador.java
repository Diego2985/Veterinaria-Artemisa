package ar.edu.unlam.tallerweb1.converter;

import com.sun.istack.NotNull;

public class IdentificacionDelPagador {

    @NotNull
    private String tipo;

    @NotNull
    private String numero;

    public IdentificacionDelPagador() {

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
