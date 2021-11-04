package ar.edu.unlam.tallerweb1.servicios;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Map;

public class DatosTiempo {
    private Integer tiempo;
    private Integer distancia;

    public Integer getTiempo() {
        return tiempo;
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
