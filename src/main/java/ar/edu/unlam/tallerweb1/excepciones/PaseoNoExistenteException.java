package ar.edu.unlam.tallerweb1.excepciones;

public class PaseoNoExistenteException extends Throwable {
    public PaseoNoExistenteException() {
        super("El usuario no tiene un paseo activo o en proceso");
    }
}
