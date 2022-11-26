package br.unitins.topicos1.farmacia.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Named;

import br.unitins.topicos1.farmacia.application.Util;
import br.unitins.topicos1.farmacia.model.Estado;
import br.unitins.topicos1.farmacia.model.Perfil;
import br.unitins.topicos1.farmacia.model.Usuario;
import br.unitins.topicos1.farmacia.repository.EstadoRepository;
import br.unitins.topicos1.farmacia.repository.UsuarioRepository;

@Named
@ViewScoped
public class UsuarioController implements Serializable {
	
	private static final long serialVersionUID = 168067236765100328L;
	private Usuario usuario = null;
	private List<Usuario> listaUsuario;
	private List<Estado> listaEstado;
	
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
	
	public void validarLogin() {
		if (getUsuario().getLogin().equals("teste123")) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Este login ja existe.", null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void incluir() {
		UsuarioRepository repo = new UsuarioRepository();
		getUsuario().setSenha(Util.hash(getUsuario().getSenha()));
		try {
			repo.salvar(getUsuario());
		} catch (Exception e) {
			Util.addErrorMessage(e.getMessage());
			e.printStackTrace();
			return;
		}
		limpar();
		// foi setado como nulo para buscar no banco
		listaUsuario = null;
	}
	
	public void alterar() {
		UsuarioRepository repo = new UsuarioRepository();
		try {
			repo.salvar(getUsuario());
		} catch (Exception e) {
			Util.addErrorMessage(e.getMessage());
			e.printStackTrace();
			return;
		}
		limpar();
		// foi setado como nulo para buscar no banco
		listaUsuario = null;
	}
	
	// acionado pelo botao excluir
	public void excluir() {
		excluir(getUsuario());
		limpar();	
	}
	
	// acionado pelo botao da tabela (excluir)
	public void excluir(Usuario usu) {
		UsuarioRepository repo = new UsuarioRepository();
		repo.deletar(usu);
		listaUsuario = null;
	}
	
	public void limpar() {
		usuario = null;
	}
	
	public void editar(Usuario usu) {
		setUsuario(usu);
		
//		setUsuario(usu.getClone());
		
//		setUsuario(new Usuario());
//		getUsuario().setId(usu.getId());
//		getUsuario().setNome(usu.getNome());
//		getUsuario().setLogin(usu.getLogin());
//		getUsuario().setSenha(usu.getSenha());
	}
	
	public List<Usuario> getListaUsuario() {
		if (listaUsuario == null) {
			UsuarioRepository repo = new UsuarioRepository();
			listaUsuario = repo.buscarTodos();
			if (listaUsuario == null)
				listaUsuario = new ArrayList<Usuario>();
		}
		return listaUsuario;
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
