package ar.edu.unlam.tallerweb1.converter;

import ar.edu.unlam.tallerweb1.modelo.TipoRecompensa;

import javax.persistence.AttributeConverter;

public class TipoRecompensaConverter implements AttributeConverter<TipoRecompensa, String> {
    @Override
    public String convertToDatabaseColumn(TipoRecompensa tipoRecompensa) {
        return tipoRecompensa.name();
    }

    @Override
    public TipoRecompensa convertToEntityAttribute(String s) {
        return TipoRecompensa.valueOf(s);
    }
}
