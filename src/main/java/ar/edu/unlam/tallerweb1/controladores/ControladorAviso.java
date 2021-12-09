package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.converter.RespuestaDePago;


import ar.edu.unlam.tallerweb1.excepciones.MercadoPagoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControladorAviso extends ResponseEntityExceptionHandler {




    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request
    ){
        Map<String, String>
        body = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach((error) -> {
            body.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MercadoPagoException.class)
    public ResponseEntity<Object> handleMercadoPagoException(MercadoPagoException exception) {
        Map<String, String> body = new HashMap<>();
        body.put("message", exception.getMessage());

        return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
