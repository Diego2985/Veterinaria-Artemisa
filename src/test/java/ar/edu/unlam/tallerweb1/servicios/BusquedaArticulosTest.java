package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Articulo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioArticulo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BusquedaArticulosTest {

    private Articulo art1=new Articulo();
    private Articulo art2=new Articulo();
    private String busqueda;
    private RepositorioArticulo repositorioArticulo=mock(RepositorioArticulo.class);
    private ServicioArticulo servicioArticulo=new ServicioArticuloImpl(repositorioArticulo);
    private List<Articulo> articulos=new ArrayList<>();

    @Before
    public void init(){
        art1.setDescripcion("Sabor carne");
        art1.setTituloArticulo("Alimentos Raza");
        art1.setPrecio(900.0F);

        art2.setDescripcion("Sabor pollo");
        art2.setTituloArticulo("Alimentos Raza");
        art2.setPrecio(1200.0F);
    }

    @Test
    public void encuentoUnSoloArticulo(){
        givenUnaListaDeArticulosConUnSoloResultado();
        List<Articulo> articulosEncontrados=whenRealizoLaBusqueda(busqueda);
        thenDeberiaRetornarUnaListaConUnSoloArticulo(articulosEncontrados);
    }

    private void givenUnaListaDeArticulosConUnSoloResultado() {
        busqueda="Sabor carne";

        articulos.add(art1);

        when(repositorioArticulo.buscarArticuloPorNombre(busqueda)).thenReturn(articulos);
    }

    private List<Articulo> whenRealizoLaBusqueda(String busqueda) {
        return servicioArticulo.buscarArticulosPorNombre(busqueda);
    }

    private void thenDeberiaRetornarUnaListaConUnSoloArticulo(List<Articulo> articulosEncontrados) {
        assertThat(articulosEncontrados).contains(art1);
    }

    @Test
    public void recibirResultadosSinDistinguirMayusculasNiMinusculas(){
        givenUnaListaDeArticulosConVariosResultadosYUnaPalabraEnMayuscula();
        List<Articulo> articulosEncontrados=whenRealizoLaBusqueda(busqueda);
        thenDeberiaRetornarLosResultadosConEsaPalabraIgual(articulosEncontrados);
    }

    private void givenUnaListaDeArticulosConVariosResultadosYUnaPalabraEnMayuscula() {
        busqueda="SABOR";

        articulos.add(art1);
        articulos.add(art2);

        when(repositorioArticulo.buscarArticuloPorNombre(busqueda)).thenReturn(articulos);
    }

    private void thenDeberiaRetornarLosResultadosConEsaPalabraIgual(List<Articulo> articulosEncontrados) {
        assertThat(articulosEncontrados).contains(art1);
        assertThat(articulosEncontrados).contains(art2);
    }
}
