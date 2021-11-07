package ar.edu.unlam.tallerweb1.excepciones;

public class DatosCambiadosException extends Throwable {
    public DatosCambiadosException(){
        super("Algunos de los datos fueron alterados");
    }
}
