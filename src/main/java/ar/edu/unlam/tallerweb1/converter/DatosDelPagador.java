package ar.edu.unlam.tallerweb1.converter;

import com.sun.istack.NotNull;

public class DatosDelPagador {

    @NotNull
    private String email;

    @NotNull
    private IdentificacionDelPagador identificacion;

    public DatosDelPagador(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public IdentificacionDelPagador getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(IdentificacionDelPagador identificacion) {
        this.identificacion = identificacion;
    }
}
