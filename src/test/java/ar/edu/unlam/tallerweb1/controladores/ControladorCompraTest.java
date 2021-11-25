package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.DatosCompra;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDatosCompra;
import ar.edu.unlam.tallerweb1.servicios.ServicioDatosCompra;
import ar.edu.unlam.tallerweb1.servicios.ServicioDatosCompraImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorCompraTest {

    /*
     * 1. Si la terjeta esta vencida no puedo realizar la compra
     * 2. Si la tarjeta no esta vencida puedo realizar la compra
     * 3. Si la tarjeta no tiene los campos completos no se realiza la compra
     */
    @Autowired
    private ModelAndView mav;
    private ControladorCompra controladorCompra = new ControladorCompra();
    private DatosCompra tarjeta = new DatosCompra("nombre", "0000000000000000", "1", "2000");
    private HttpServletRequest mockHttpServletRequest;
    HttpSession sessionMock = mock(HttpSession.class);


    // private ServicioDatosCompra servicioDatosCompra = new ServicioDatosCompraImpl();
   // private final RepositorioDatosCompra repositorioDatosCompra = mock(RepositorioDatosCompra.class);

    @Before
    public void init() {
        tarjeta.setNombre("Diego");
        tarjeta.setNumeroCredito("1111222233334444");
        tarjeta.setMes("8");
        tarjeta.setAnio("2024");

        mockHttpServletRequest = mock(HttpServletRequest.class);

        when(mockHttpServletRequest.getSession()).thenReturn(sessionMock);
        when(mockHttpServletRequest.getSession().getAttribute("userId")).thenReturn(1L);
    }

    @Test
    public void siLaTarjetaEstaVencidaNoPuedoRealizarLaCompra(){
            givenLaTarjetaEstaVencida();
            whenCompletoLosCamposDeLaTarjetaConFechaVencida();
            thenNoPuedoRealizarLaCompra("La tarjeta esta vencida");
        }
    private void givenLaTarjetaEstaVencida() {
    }

    private void whenCompletoLosCamposDeLaTarjetaConFechaVencida() {
      DatosCompra datosCompra = new DatosCompra("Diego", "1111222233334444", "8", "2019");
      mav = controladorCompra.datosTarjeta(datosCompra);
    }

    private void thenNoPuedoRealizarLaCompra(String mensaje) {
        // me lleva a una nueva vista, porque esta vencida la tarjeta
        assertThat(mav.getViewName()).isEqualTo("compra-fallida");
        // muestro un mensaje de error
        assertThat(mav.getModel().get("error")).isEqualTo(mensaje);
    }
    @Test
    public void siLaTarjetaNoEstaVencidaPuedoRealizarLaCompra(){
        givenLaTarjetaNoEstaVencida();
        whenCompletoLosCamposDeLaTarjetaConFechaNoVencida();
        thenLaCompraFueExitosa();
    }

    private void givenLaTarjetaNoEstaVencida() {
    }

    private void whenCompletoLosCamposDeLaTarjetaConFechaNoVencida() {
        DatosCompra datosCompra = new DatosCompra("Diego", "1111222233334444", "8", "2024");
        mav = controladorCompra.confirmarCompra(mockHttpServletRequest);
    }
    private void thenLaCompraFueExitosa() {
        assertThat(mav.getViewName()).isEqualTo("compra-exitosa");
    }

   // @Test
    //public void SiLaTarjetaNoTieneLosCamposCompletosNoSeRealizaLaCompra() {
      //  givenExisteLaTarjeta();
      //  whenLosCamposEstanIncompletos();
      //  thenLaCompraNoSePuedeRealizar();
   // }

   // private void givenExisteLaTarjeta() {
    //}

   // private void whenLosCamposEstanIncompletos() {
     //   DatosCompra datosCompra = new DatosCompra(null, null, null, null);
     //  mav = controladorCompra.aprobarTarjeta(datosCompra);
    //}

    //private void thenLaCompraNoSePuedeRealizar() {
      //  assertThat(mav.getViewName()).isEqualTo("compra-articulo");
   // }
}