package ar.edu.unlam.tallerweb1.modelo;

public class DatosCompra {

    private String nombre;
    private String numeroCredito;
    private String mes;
    private String anio;


    public DatosCompra(){

    }

    public DatosCompra(String nombre, String numeroCredito, String mes, String anio) {
        this.nombre = nombre;
        this.numeroCredito = numeroCredito;
        this.mes = mes;
        this.anio = anio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroCredito() {
        return numeroCredito;
    }

    public void setNumeroCredito(String numeroCredito) {
        this.numeroCredito = numeroCredito;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }
}
