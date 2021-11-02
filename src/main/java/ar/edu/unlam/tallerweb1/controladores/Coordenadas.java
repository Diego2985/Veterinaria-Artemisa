package ar.edu.unlam.tallerweb1.controladores;

public class Coordenadas {
    private Double latitud;
    private Double longitud;
    private String coordenadas;

    public Coordenadas(String coordenadas, Double latitud, Double longitud) {
        this.coordenadas = coordenadas;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }
}
