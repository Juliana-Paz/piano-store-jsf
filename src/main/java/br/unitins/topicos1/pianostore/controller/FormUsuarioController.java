package br.unitins.topicos1.pianostore.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicos1.pianostore.application.Util;
import br.unitins.topicos1.pianostore.model.Estado;
import br.unitins.topicos1.pianostore.model.Perfil;
import br.unitins.topicos1.pianostore.model.Usuario;
import br.unitins.topicos1.pianostore.repository.EstadoRepository;
import br.unitins.topicos1.pianostore.repository.UsuarioRepository;

@Named
@ViewScoped
public class FormUsuarioController implements Serializable {

	private static final long serialVersionUID = -4197665569375141413L;
	private Usuario usuario = null;

	public FormUsuarioController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("flashUsuario");
		setUsuario((Usuario) flash.get("flashUsuario"));
	}

	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}

	public String salvar() {
		UsuarioRepository repo = new UsuarioRepository();
		try {
			getUsuario().setSenha(Util.hash(getUsuario().getSenha()));
			
			repo.salvar(getUsuario());
		} catch (Exception e) {
			Util.addErrorMessage(e.getMessage());
			e.printStackTrace();
			return null;
		}
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(true);

		Util.addInfoMessage("Usuario salvo com sucesso.");

		return cancelar();
	}

	public String cancelar() {
		return "/admin/usuarios.xhtml?faces-redirect=true";
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
