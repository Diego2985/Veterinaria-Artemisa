package ar.edu.unlam.tallerweb1.excepciones;

public class PaseoIniciadoException extends Throwable {
    public PaseoIniciadoException() {
        super("El usuario tiene un paseo activo");
    }
}
