package br.unitins.topicos1.farmacia.controller;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import br.unitins.topicos1.farmacia.application.RepositoryException;
import br.unitins.topicos1.farmacia.application.Util;
import br.unitins.topicos1.farmacia.model.Usuario;
import br.unitins.topicos1.farmacia.repository.UsuarioRepository;

@Named
@RequestScoped
public class LoginController {

	private Usuario usuario;
	
	public String logar() {
		
		UsuarioRepository repo = new UsuarioRepository();
		
		Usuario usuarioLogado;
		try {
			usuarioLogado = repo.buscar(getUsuario().getLogin(), getUsuario().getSenha());
		} catch (RepositoryException e) {
			// quando entrar nesse exception, significa que o usuario não foi encontrado
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
