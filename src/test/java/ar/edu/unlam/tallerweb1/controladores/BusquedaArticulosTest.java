package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Articulo;
import ar.edu.unlam.tallerweb1.servicios.ServicioArticulo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BusquedaArticulosTest {
    private String busqueda;
    private ModelAndView mav;
    Articulo art1=new Articulo();
    Articulo art2=new Articulo();
    private ServicioArticulo servicioArticulo=mock(ServicioArticulo.class);
    private ControladorArticulo controladorArticulo=new ControladorArticulo(servicioArticulo);

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
    public void encuentroUnArticuloYLoMandoALavista(){
        givenUnaListaDeArticulosEncontradosConUnNombre();
        mav=whenRealizoLaBusqueda(busqueda);
        thenEncuentroElArticuloEspecifico(mav);
    }

    private void givenUnaListaDeArticulosEncontradosConUnNombre() {
        busqueda="Sabor pollo";
        List<Articulo> articulos=new ArrayList<>();
        articulos.add(art2);
        when(servicioArticulo.buscarArticulosPorNombre(busqueda)).thenReturn(articulos);
    }

    private ModelAndView whenRealizoLaBusqueda(String busqueda) {
        return controladorArticulo.buscarArticulosPorNombre(busqueda);
    }

    private void thenEncuentroElArticuloEspecifico(ModelAndView mav) {
        List<Articulo> articulosEncontrados= (List<Articulo>) mav.getModel().get("articulos");
        assertThat(articulosEncontrados).contains(art2);
    }

    @Test
    public void encuentroVariosArticulosConUnaPalabraEnComun(){
        givenUnaListaDeArticulosEncontradosConNombreEnComun();
        mav=whenRealizoLaBusqueda(busqueda);
        thenDeberianEstarLosProductosQueCoincidenConEsaPalabra(mav);
    }

    private void givenUnaListaDeArticulosEncontradosConNombreEnComun() {
        busqueda="Sabor";
        List<Articulo> articulos=new ArrayList<>();
        articulos.add(art1);
        articulos.add(art2);

        when(servicioArticulo.buscarArticulosPorNombre(busqueda)).thenReturn(articulos);
    }

    private void thenDeberianEstarLosProductosQueCoincidenConEsaPalabra(ModelAndView mav) {
        List<Articulo> articulosEncontrados= (List<Articulo>) mav.getModel().get("articulos");
        assertThat(articulosEncontrados).contains(art1);
        assertThat(articulosEncontrados).contains(art2);
    }

    @Test
    public void noEncuentraNingunArticuloConUnNombre(){
        givenUnaPalabraQueNoCoincideConNingunArticulo();
        mav=whenRealizoLaBusqueda(busqueda);
        thenDeberiaRetornarUnaListaVacia();
    }

    private void givenUnaPalabraQueNoCoincideConNingunArticulo() {
        busqueda="pescado";
        when(servicioArticulo.buscarArticulosPorNombre(busqueda)).thenReturn(new ArrayList<>());
    }

    private void thenDeberiaRetornarUnaListaVacia() {
        List<Articulo> articulosEncontrados= (List<Articulo>) mav.getModel().get("articulos");
        assertThat(articulosEncontrados).hasSize(0);
    }
}
