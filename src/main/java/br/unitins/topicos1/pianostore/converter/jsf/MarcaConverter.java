package br.unitins.topicos1.pianostore.converter.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import br.unitins.topicos1.pianostore.model.Marca;
import br.unitins.topicos1.pianostore.repository.MarcaRepository;

@Named
@FacesConverter(forClass = Marca.class)
public class MarcaConverter implements Converter<Marca>{

	@Override
	public Marca getAsObject(FacesContext context, UIComponent component, String id) {
		if (id == null || id.isBlank())
			return null;
		MarcaRepository marca = new MarcaRepository();
		return marca.buscarPeloId(Integer.valueOf(id));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Marca marca) {
		if (marca == null || marca.getId() == null)
			return null;
		
		return marca.getId().toString();
	}

}
