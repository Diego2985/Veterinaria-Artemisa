package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Articulo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class BusquedaArticulosTest extends SpringTest {
    private Articulo art1=new Articulo();
    private Articulo art2=new Articulo();
    private String busqueda;
    @Autowired
    private RepositorioArticulo repositorioArticulo;

    @Before
    public void init(){
        art1.setDescripcion("Sabor carne");
        art1.setTituloArticulo("Alimentos Raza");
        art1.setPrecio(900.0F);

        art2.setDescripcion("Sabor pollo");
        art2.setTituloArticulo("Alimentos Raza");
        art2.setPrecio(1200.0F);
    }

    @Rollback @Transactional
    @Test
    public void encuentroUnSoloResultado(){
        givenUnArticulo();
        List<Articulo> articulos=whenRealizoLaBusqueda(busqueda);
        thenMeDevuelveUnaListaConUnSoloResultado(articulos);
    }

    private void givenUnArticulo() {
        busqueda="carne";
        session().save(art1);
    }

    private List<Articulo> whenRealizoLaBusqueda(String busqueda) {
        return repositorioArticulo.buscarArticuloPorNombre(busqueda);
    }

    private void thenMeDevuelveUnaListaConUnSoloResultado(List<Articulo> articulos) {
        assertThat(articulos).hasSize(1);
        assertThat(articulos).contains(art1);
    }
}
