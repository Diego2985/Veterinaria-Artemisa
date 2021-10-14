package ar.edu.unlam.tallerweb1.modelo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class DatosTurno {

    private String fechaDesde;
    private String fechaHasta;
    private String fecha;
    private List<String> horas;
    private String horaSeleccionada;
    private Double precio;
    private List<String> serviciosSeleccionados;

    public DatosTurno(Date fecha, Double precio) {
        this.fecha = fecha.toString();
        this.precio = precio;
    }

    public DatosTurno() {}

    public String getFechaDesde() {
        return fechaDesde;
    }

    public DatosTurno setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
        return this;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    public DatosTurno setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
        return this;
    }

    public String getFecha() {
        return fecha;
    }

    public DatosTurno setFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }

    public List<String> getHoras() {
        return horas;
    }

    public DatosTurno setHoras(List<String> horas) {
        this.horas = horas;
        return this;
    }

    public Double getPrecio() {
        return precio;
    }

    public DatosTurno setPrecio(Double precio) {
        this.precio = precio;
        return this;
    }

    public List<String> getServiciosSeleccionados() {
        return serviciosSeleccionados;
    }

    public DatosTurno setServiciosSeleccionados(List<String> serviciosSeleccionados) {
        this.serviciosSeleccionados = serviciosSeleccionados;
        return this;
    }

    public String getHoraSeleccionada() {
        return horaSeleccionada;
    }

    public DatosTurno setHoraSeleccionada(String horaSeleccionada) {
        this.horaSeleccionada = horaSeleccionada;
        return this;
    }
}
