package br.unitins.topicos1.farmacia.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicos1.farmacia.model.Usuario;
import br.unitins.topicos1.farmacia.repository.UsuarioRepository;

@Named
@ViewScoped
public class EstadoController implements Serializable {

	private static final long serialVersionUID = 940590651764702687L;
	private List<Usuario> listaUsuario;
	
	public List<Usuario> getListaUsuario() {
		if (listaUsuario == null) {
			UsuarioRepository repo = new UsuarioRepository();
			listaUsuario = repo.buscarTodos();
			if (listaUsuario == null)
				listaUsuario = new ArrayList<Usuario>();
		}
		return listaUsuario;
	}
	
	public String editar(Usuario usuario) {
		// flash scoped
		Flash flash = FacesContext.
						getCurrentInstance().
						getExternalContext().getFlash();
		
		flash.put("flashUsuario", usuario);
		
		return "formusuario.xhtml?faces-redirect=true";
	}
	
	public void excluir(Usuario usuario) {
		
	}
	
	

}
