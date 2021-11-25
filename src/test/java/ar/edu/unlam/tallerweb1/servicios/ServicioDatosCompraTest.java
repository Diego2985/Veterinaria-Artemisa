package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Articulo;
import ar.edu.unlam.tallerweb1.modelo.Compra;
import ar.edu.unlam.tallerweb1.modelo.DatosCompra;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDatosCompra;
import org.junit.Before;
import org.junit.Test;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServicioDatosCompraTest {

    private Articulo articulo;
    private Usuario usuario;
    private DatosCompra tarjeta = new DatosCompra("nombre", "0000000000000000", "1", "2000");
    //private ServicioDatosCompra servicioDatosCompra = new ServicioDatosCompraImpl();
    private final RepositorioDatosCompra repositorioDatosCompra = mock(RepositorioDatosCompra.class);


    @Before
    public void init(){
        tarjeta.setNombre("Diego");
        tarjeta.setNumeroCredito("1111222233334444");
        tarjeta.setMes("8");
        tarjeta.setAnio("2024");

    }
    @Test
    public void testQueLaTarjetaDeCreditoNoEsteVencida(){
        givenDatosTarjeta();
        whenBuscoLaTarjeta();
        thenDevuelvoResultadoDeLaBusqueda();
    }

    private void givenDatosTarjeta() {

    }

    private void whenBuscoLaTarjeta() {

    }

    private void thenDevuelvoResultadoDeLaBusqueda() {
    }


}
