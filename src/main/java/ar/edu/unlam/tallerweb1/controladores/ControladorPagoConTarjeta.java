package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.converter.DatosPagoConTarjeta;

import ar.edu.unlam.tallerweb1.converter.RespuestaDePago;

import ar.edu.unlam.tallerweb1.modelo.OrderData;
import ar.edu.unlam.tallerweb1.servicios.PagoConTarjetaServicio;
import com.google.gson.Gson;
import com.google.gson.JsonNull;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControladorPagoConTarjeta {

    private PagoConTarjetaServicio pagoConTarjetaServicio;

    @Autowired
    public ControladorPagoConTarjeta(PagoConTarjetaServicio pagoConTarjetaServicio) {
        this.pagoConTarjetaServicio = pagoConTarjetaServicio;
    }

    @RequestMapping(path =  "/process_payment", method = RequestMethod.POST)
    public ResponseEntity procesarPago(@RequestBody @Validated DatosPagoConTarjeta datosPagoConTarjeta) throws MPException {
        RespuestaDePago pago = pagoConTarjetaServicio.procesoDePago(datosPagoConTarjeta);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(path = "/create_preference", method = RequestMethod.POST)
    public ResponseEntity<String> crearPreferencia(@RequestBody @Validated OrderData orderData) throws MPException {
        // Agrega credenciales
        MercadoPago.SDK.setAccessToken("TEST-441666672441213-120702-b301080f64acfd6b5c50cbffb38ab8b7-130399654");
        // Crea un objeto de preferencia
        Preference preference = new Preference();

        // Crea un ítem en la preferencia
        Item item = new Item();
        item.setTitle(orderData.getTitle())
                .setDescription(orderData.getDescription())
                .setQuantity(orderData.getQuantity())
                .setUnitPrice(orderData.getPrice());
        preference.appendItem(item);
        preference.setBackUrls(
                new BackUrls()
                .setSuccess("http://localhost:8080/Veterinaria_Artemisa_war_exploded/articulos")
                .setPending("http://localhost:8080/Veterinaria_Artemisa_war_exploded/articulos")
                .setFailure("http://localhost:8080/Veterinaria_Artemisa_war_exploded/articulos")
        );

        preference.save();

        return ResponseEntity.status(HttpStatus.OK).body(preference.getId());
    }
}
