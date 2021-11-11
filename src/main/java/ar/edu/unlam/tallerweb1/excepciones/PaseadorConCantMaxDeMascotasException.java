package ar.edu.unlam.tallerweb1.excepciones;

public class PaseadorConCantMaxDeMascotasException extends Exception{
    public PaseadorConCantMaxDeMascotasException(){
        super("El paseador llegó al límite de mascotas las cuales puede tener en su poder. Por favor elija otro paseador");
    }
}
