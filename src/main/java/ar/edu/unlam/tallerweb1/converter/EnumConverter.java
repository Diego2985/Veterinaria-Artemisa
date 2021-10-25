package ar.edu.unlam.tallerweb1.converter;

import ar.edu.unlam.tallerweb1.modelo.TurnoEstado;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EnumConverter implements AttributeConverter<TurnoEstado, String> {

    @Override
    public String convertToDatabaseColumn(TurnoEstado turnoEstado) {
        return turnoEstado.name();
    }

    @Override
    public TurnoEstado convertToEntityAttribute(String s) {
        return TurnoEstado.valueOf(s);
    }
}
