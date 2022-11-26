package br.unitins.topicos1.farmacia.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicos1.farmacia.application.Util;
import br.unitins.topicos1.farmacia.model.Estado;
import br.unitins.topicos1.farmacia.model.Perfil;
import br.unitins.topicos1.farmacia.model.Usuario;
import br.unitins.topicos1.farmacia.repository.EstadoRepository;
import br.unitins.topicos1.farmacia.repository.UsuarioRepository;

@Named
@ViewScoped
public class FormUsuarioController implements Serializable {
	
	private static final long serialVersionUID = -4197665569375141413L;
	private Usuario usuario = null;
	private List<Estado> listaEstado;
	
	public FormUsuarioController() {
		// obtendo um usuario do flash scoped
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		// metodo que nao deixa apagar o objeto enquanto nao alterar a url
		flash.keep("flashUsuario");
		setUsuario((Usuario)flash.get("flashUsuario"));
	}
	
	public List<Estado> getListaEstado() {
		if (listaEstado == null) { 
			EstadoRepository repo = new EstadoRepository();
			listaEstado = repo.buscarTodos();
		}
		return listaEstado;
	}
	
	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}
	
	public String salvar() {
		UsuarioRepository repo = new UsuarioRepository();
		try {
			repo.salvar(getUsuario());
		} catch (Exception e) {
			Util.addErrorMessage(e.getMessage());
			e.printStackTrace();
			return null;
		}
		// PARA HABILITAR O ENVIO DE MENSAGEM ENTRE TELAS DIFERENTES
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(true);
		
		Util.addInfoMessage("Usuario salvo com sucesso.");
		
		// o cancelar retorna para a pagina anterior
		return cancelar();
	}
	
	public String cancelar() {
		return "usuarios.xhtml?faces-redirect=true";
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
