package br.unitins.topicos1.pianostore.converter.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import br.unitins.topicos1.pianostore.model.SubTipo;
import br.unitins.topicos1.pianostore.repository.SubTipoRepository;

@Named
@FacesConverter(forClass = SubTipo.class)
public class SubTipoConverter implements Converter<SubTipo>{

	@Override
	public SubTipo getAsObject(FacesContext context, UIComponent component, String id) {
		if (id == null || id.isBlank())
			return null;
		SubTipoRepository subTipo = new SubTipoRepository();
		return subTipo.buscarPeloId(Integer.valueOf(id));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, SubTipo subTipo) {
		if (subTipo == null || subTipo.getId() == null)
			return null;
		
		return subTipo.getId().toString();
	}

}
