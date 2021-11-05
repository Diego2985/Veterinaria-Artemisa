package ar.edu.unlam.tallerweb1.converter;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Map;

public class Ubicacion {
    private String calle;
    private Integer numero;
    private String ciudad;
    private Coordenadas coordenadas;

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
    }

    @JsonProperty("items")
    private void unpackNameFromNestedObject(ArrayList<Object> brand) {
        Map<String, Object> address = (Map<String, Object>) ((Map<String, Object>) brand.get(0)).get("address");
        Map<String, Double> position = (Map<String, Double>) ((Map<String, Object>) brand.get(0)).get("position");
        this.calle = (String) address.get("street");
        this.numero = Integer.parseInt((String) address.get("houseNumber"));
        this.ciudad = (String) address.get("city");
        Double latitud = position.get("lat");
        Double longitud = position.get("lng");
        this.coordenadas = new Coordenadas(latitud, longitud);
    }

    @Override
    public String toString() {
        return this.calle+" "+this.numero+", "+this.ciudad+".";
    }
}
