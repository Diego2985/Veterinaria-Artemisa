package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class RegistroPaseo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Paseador paseador;
    @OneToOne
    private Usuario usuario;
    private Date horaInicio;
    private Date horaFinal;
    private Integer estado=0;
    @OneToOne
    private Mascota mascota;
    private String domicilio;
    private Double latitudUsuario;
    private Double longitudUsuario;

    public Double getLatitudUsuario() {
        return latitudUsuario;
    }

    public void setLatitudUsuario(Double latitudUsuario) {
        this.latitudUsuario = latitudUsuario;
    }

    public Double getLongitudUsuario() {
        return longitudUsuario;
    }

    public void setLongitudUsuario(Double longitudUsuario) {
        this.longitudUsuario = longitudUsuario;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paseador getPaseador() {
        return paseador;
    }

    public void setPaseador(Paseador paseador) {
        this.paseador = paseador;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Date horaFinal) {
        this.horaFinal = horaFinal;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
}
