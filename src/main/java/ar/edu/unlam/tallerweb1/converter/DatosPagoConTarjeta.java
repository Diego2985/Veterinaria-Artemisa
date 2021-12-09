package ar.edu.unlam.tallerweb1.converter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

public class DatosPagoConTarjeta {

    @NotNull
    private String token;

    private String editorID;

    @NotNull
    private String metodoDePagoId;

    @NotNull
    private Float cantidadDeTransaccion;

    @NotNull
    private Integer cuotas;

    @NotNull
    @JsonProperty("descripcion")
    private String descripcionProducto;

    @NotNull
    private  DatosDelPagador pagador;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEditorID() {
        return editorID;
    }

    public void setEditorID(String editorID) {
        this.editorID = editorID;
    }

    public String getMetodoDePagoId() {
        return metodoDePagoId;
    }

    public void setMetodoDePagoId(String metodoDePagoId) {
        this.metodoDePagoId = metodoDePagoId;
    }

    public Float getCantidadDeTransaccion() {
        return cantidadDeTransaccion;
    }

    public void setCantidadDeTransaccion(Float cantidadDeTransaccion) {
        this.cantidadDeTransaccion = cantidadDeTransaccion;
    }

    public Integer getCuotas() {
        return cuotas;
    }

    public void setCuotas(Integer cuotas) {
        this.cuotas = cuotas;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public DatosDelPagador getPagador() {
        return pagador;
    }

    public void setPagador(DatosDelPagador pagador) {
        this.pagador = pagador;
    }
}
