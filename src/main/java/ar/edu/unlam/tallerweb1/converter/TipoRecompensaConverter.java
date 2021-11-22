package ar.edu.unlam.tallerweb1.converter;

import ar.edu.unlam.tallerweb1.modelo.TipoNotificacion;

import javax.persistence.AttributeConverter;

public class TipoRecompensaConverter implements AttributeConverter<TipoNotificacion, String> {
    @Override
    public String convertToDatabaseColumn(TipoNotificacion tipoNotificacion) {
        return tipoNotificacion.name();
    }

    @Override
    public TipoNotificacion convertToEntityAttribute(String s) {
        return TipoNotificacion.valueOf(s);
    }
}
