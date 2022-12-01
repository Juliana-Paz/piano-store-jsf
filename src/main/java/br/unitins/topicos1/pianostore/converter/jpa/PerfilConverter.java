package br.unitins.topicos1.pianostore.converter.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.topicos1.pianostore.model.Perfil;

@Converter(autoApply = true)
public class PerfilConverter implements AttributeConverter<Perfil, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Perfil perfil) {
		return perfil.getId();
	}

	@Override
	public Perfil convertToEntityAttribute(Integer id) {
		return Perfil.valueOf(id);
	}

}
