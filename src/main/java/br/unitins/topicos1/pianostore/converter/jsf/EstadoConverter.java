package br.unitins.topicos1.pianostore.converter.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import br.unitins.topicos1.pianostore.model.Estado;
import br.unitins.topicos1.pianostore.repository.EstadoRepository;

@Named
@FacesConverter(forClass = Estado.class)
public class EstadoConverter implements Converter<Estado>{

	@Override
	public Estado getAsObject(FacesContext context, UIComponent component, String id) {
		if (id == null || id.isBlank())
			return null;
		EstadoRepository estado = new EstadoRepository();
		return estado.buscarPeloId(Integer.valueOf(id));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Estado estado) {
		if (estado == null || estado.getId() == null)
			return null;
		
		return estado.getId().toString();
	}

}
