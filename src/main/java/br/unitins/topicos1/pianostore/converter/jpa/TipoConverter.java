package br.unitins.topicos1.pianostore.converter.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.topicos1.pianostore.model.Tipo;

@Converter(autoApply = true)
public class TipoConverter implements AttributeConverter<Tipo, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Tipo tipo) {
		return tipo.getId();
	}

	@Override
	public Tipo convertToEntityAttribute(Integer id) {
		return Tipo.valueOf(id);
	}

}
