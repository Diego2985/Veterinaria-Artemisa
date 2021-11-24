package ar.edu.unlam.tallerweb1.converter;

import ar.edu.unlam.tallerweb1.modelo.RegistroPaseo;

public class PaseoActivo {
    private RegistroPaseo paseo;
    private Integer tiempoRestante;
    private String imgPosicionPaseador;

    public PaseoActivo(RegistroPaseo paseo, Integer tiempoRestante, String imgPosicionPaseador) {
        this.paseo = paseo;
        this.tiempoRestante = tiempoRestante;
        this.imgPosicionPaseador = imgPosicionPaseador;
    }

    public RegistroPaseo getPaseo() {
        return paseo;
    }

    public void setPaseo(RegistroPaseo paseo) {
        this.paseo = paseo;
    }

    public Integer getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(Integer tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    public String getImgPosicionPaseador() {
        return imgPosicionPaseador;
    }

    public void setImgPosicionPaseador(String imgPosicionPaseador) {
        this.imgPosicionPaseador = imgPosicionPaseador;
    }
}
