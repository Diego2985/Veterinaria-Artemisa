package ar.edu.unlam.tallerweb1.excepciones;

public class PaseoNoIniciadoException extends Throwable {
    public PaseoNoIniciadoException() {
        super("El usuario tiene un paseo por iniciar");
    }
}
