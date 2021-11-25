package ar.edu.unlam.tallerweb1.excepciones;

public class TodosLosPerrosConPaseoActivoException extends Exception {
    public TodosLosPerrosConPaseoActivoException() {
        super("Todos los perros del usuario tienen un paseo activo");
    }
}
