package ar.edu.unlam.tallerweb1.converter;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Map;

public class DatosTiempo {
    private Integer tiempo;
    private Integer distancia;
    private Coordenadas coordenadasUsuario;
    private Coordenadas coordenadasPaseador;
    private Integer unidadSegundosAMinutos;

    public DatosTiempo() {
        this.unidadSegundosAMinutos=60;
    }

    public Coordenadas getCoordenadasUsuario() {
        return coordenadasUsuario;
    }

    public void setCoordenadasUsuario(Coordenadas coordenadasUsuario) {
        this.coordenadasUsuario = coordenadasUsuario;
    }

    public Coordenadas getCoordenadasPaseador() {
        return coordenadasPaseador;
    }

    public void setCoordenadasPaseador(Coordenadas coordenadasPaseador) {
        this.coordenadasPaseador = coordenadasPaseador;
    }

    public Integer getTiempo() {
        return Math.round(tiempo / this.unidadSegundosAMinutos);
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public Integer getDistancia() {
        return distancia;
    }

    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }

    @JsonProperty("response")
    private void unpackNameFromNestedObject(Map<String, Object> brand) {
        Map<String,Integer> response= (Map<String, Integer>) ((Map<String,Object>)((ArrayList<Object>)brand.get("route")).get(0)).get("summary");
        this.tiempo=response.get("baseTime");
        this.distancia =response.get("distance");
    }
}
