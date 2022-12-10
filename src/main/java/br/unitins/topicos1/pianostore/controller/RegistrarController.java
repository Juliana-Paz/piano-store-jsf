package br.unitins.topicos1.pianostore.controller;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.unitins.topicos1.pianostore.application.Util;
import br.unitins.topicos1.pianostore.model.Perfil;
import br.unitins.topicos1.pianostore.model.Usuario;
import br.unitins.topicos1.pianostore.repository.UsuarioRepository;

@Named
@RequestScoped
public class RegistrarController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6039479895836092936L;
	private Usuario usuario;

	public String registrar() {
		getUsuario().setPerfil(Perfil.CLIENTE);
		getUsuario().setAtivo(true);
		getUsuario().setSenha(Util.hash(getUsuario().getSenha()));
		UsuarioRepository repo = new UsuarioRepository();

		try {
			repo.salvar(getUsuario());
			Util.addInfoMessage("Usuário criado com sucesso.");

			Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			session.put("usuarioLogado", getUsuario());

			return "home.xhtml?faces-redirect=true";

		} catch (Exception e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
			return null;

		}

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
