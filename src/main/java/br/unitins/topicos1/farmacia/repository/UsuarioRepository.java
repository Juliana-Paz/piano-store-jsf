package br.unitins.topicos1.farmacia.repository;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.topicos1.farmacia.application.RepositoryException;
import br.unitins.topicos1.farmacia.application.Util;
import br.unitins.topicos1.farmacia.model.Usuario;

public class UsuarioRepository extends Repository<Usuario> {

	public List<Usuario> buscarTodos() {
		Query query = getEntityManager().createQuery("SELECT u FROM Usuario u");
		return query.getResultList();
	}
	
	public Usuario buscar(String login, String senha) throws RepositoryException {
		
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT "); 
		jpql.append("  u ");
		jpql.append("FROM ");
		jpql.append("  Usuario u ");
		jpql.append("WHERE ");
		jpql.append("  u.login = :pLogin AND ");
		jpql.append("  u.senha = :pSenha ");
		
		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("pLogin", login);
		query.setParameter("pSenha", senha);
		
		try {
			// o metodo getSingleResult gera um eception quando nao existe um resultado
			return (Usuario)query.getSingleResult();
		} catch (NoResultException e) {
			throw new RepositoryException("Login ou senha Inválido");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
