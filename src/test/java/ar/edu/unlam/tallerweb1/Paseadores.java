package ar.edu.unlam.tallerweb1;

import ar.edu.unlam.tallerweb1.modelo.Paseador;

import java.util.ArrayList;
import java.util.List;

public class Paseadores {
    public static List<Paseador> crearPaseadores() {
        Paseador paseador1 = crearPaseador(-34.58856, -58.41066);
        Paseador paseador2 = crearPaseador(-34.585991, -58.407848);
        Paseador paseador3 = crearPaseador(-34.58581, -58.41485);

        List<Paseador> paseadores=new ArrayList<>();
        paseadores.add(paseador1);
        paseadores.add(paseador2);
        paseadores.add(paseador3);

        return paseadores;
    }

    public static Paseador crearPaseador(Double latitud, Double longitud) {
        Paseador paseador = new Paseador();
        paseador.setEstrellas(5);
        paseador.setLatitud(latitud);
        paseador.setLongitud(longitud);

        return paseador;
    }
}
