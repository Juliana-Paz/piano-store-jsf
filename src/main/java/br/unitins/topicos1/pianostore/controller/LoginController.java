package br.unitins.topicos1.pianostore.controller;

import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.unitins.topicos1.pianostore.application.RepositoryException;
import br.unitins.topicos1.pianostore.application.Util;
import br.unitins.topicos1.pianostore.model.Usuario;
import br.unitins.topicos1.pianostore.repository.UsuarioRepository;

@Named
@RequestScoped
public class LoginController {

	private Usuario usuario;
	
	public String logar() {
		
		UsuarioRepository repo = new UsuarioRepository();
		
		Usuario usuarioLogado;
		try {
			usuarioLogado = repo.buscar(getUsuario().getLogin(), Util.hash(getUsuario().getSenha()));
		} catch (RepositoryException e) {
			// quando entrar nesse exception, significa que o usuario n�o foi encontrado
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
			return null;
		}
		
		Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		session.put("usuarioLogado", usuarioLogado);
		
		return "home.xhtml?faces-redirect=true";
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	

}
