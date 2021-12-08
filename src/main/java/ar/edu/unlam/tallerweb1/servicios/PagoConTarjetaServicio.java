package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.converter.DatosPagoConTarjeta;
import ar.edu.unlam.tallerweb1.excepciones.MercadoPagoException;
import com.mercadopago.MercadoPago;
import ar.edu.unlam.tallerweb1.converter.RespuestaDePago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.payment.Identification;
import com.mercadopago.resources.datastructures.payment.Payer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PagoConTarjetaServicio {

    @Value("TEST-0bd8f6ff-620f-4df7-a047-670ca51a949d")
    private String mercadoPagoAccessToken;

    public RespuestaDePago procesoDePago(DatosPagoConTarjeta datosPagoConTarjeta) throws MPException {
        try {
            MercadoPago.SDK.setAccessToken(mercadoPagoAccessToken);

            Payment payment = new Payment();
            payment.setTransactionAmount(datosPagoConTarjeta.getCantidadDeTransaccion())
                    .setToken(datosPagoConTarjeta.getToken())
                    .setDescription(datosPagoConTarjeta.getDescripcionProducto())
                    .setInstallments(datosPagoConTarjeta.getCuotas())
                    .setPaymentMethodId(datosPagoConTarjeta.getMetodoDePagoId());

            Identification identification = new Identification();
            identification.setType(datosPagoConTarjeta.getPagador().getIdentificacion().getTipo())
                    .setNumber(datosPagoConTarjeta.getPagador().getIdentificacion().getNumero());

            Payer payer = new Payer();
            payer.setEmail(datosPagoConTarjeta.getPagador().getEmail());
            payer.setIdentification(identification);

            payment.setPayer(payer);

            Payment createdPayment = payment.save();

            RespuestaDePago respuestaDePago = new RespuestaDePago(
                    createdPayment.getId(),
                    String.valueOf(createdPayment.getStatus()),
                    createdPayment.getStatusDetail()
            );

            return respuestaDePago;
        } catch (MPConfException exception) {
            throw new MercadoPagoException(exception.getMessage());
        } catch (MPException e) {
            throw new MPException(e.getMessage());
        }
    }
}
