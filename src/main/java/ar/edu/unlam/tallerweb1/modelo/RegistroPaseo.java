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

    private Integer estado=0;
}
