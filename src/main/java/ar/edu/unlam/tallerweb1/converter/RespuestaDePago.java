package ar.edu.unlam.tallerweb1.converter;

public class RespuestaDePago {
  private String id;
  private String estado;
  private String detalles;

    public RespuestaDePago(String id, String estado, String detalles) {
        this.id = id;
        this.estado = estado;
        this.detalles = detalles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
}
