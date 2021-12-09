package ar.edu.unlam.tallerweb1.excepciones;

public class MercadoPagoException extends RuntimeException {
    public MercadoPagoException(String message) {
        super(message);
    }
}
