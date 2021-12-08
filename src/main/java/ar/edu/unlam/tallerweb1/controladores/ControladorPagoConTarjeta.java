package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.converter.DatosPagoConTarjeta;

import ar.edu.unlam.tallerweb1.converter.RespuestaDePago;

import ar.edu.unlam.tallerweb1.servicios.PagoConTarjetaServicio;
import com.mercadopago.exceptions.MPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
