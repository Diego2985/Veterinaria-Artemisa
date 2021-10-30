package ar.edu.unlam.tallerweb1.modelo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class DatosTurno {

    private String fechaDesde;
    private String fechaHasta;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fecha;
    private List<String> horas;
    private String horaSeleccionada;
    private Double precio;
    private List<Servicio> servicios;
    private List<String> serviciosSeleccionados;

    public DatosTurno(Date fecha, Double precio, List<Servicio> servicios) {
        this.fecha = fecha;
        this.precio = precio;
        this.servicios = servicios;
    }

    public DatosTurno() {

    }

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

    public Date getFecha() {
        return fecha;
    }

    public DatosTurno setFecha(Date fecha) {
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

    public List<Servicio> getServicios() {
        return servicios;
    }

    public DatosTurno setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
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
