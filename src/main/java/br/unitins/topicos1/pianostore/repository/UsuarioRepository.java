package br.unitins.topicos1.pianostore.repository;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.topicos1.pianostore.application.RepositoryException;
import br.unitins.topicos1.pianostore.model.Usuario;

public class UsuarioRepository extends Repository<Usuario> {

	public List<Usuario> buscarTodos() {
		Query query = getEntityManager().createQuery("SELECT u FROM Usuario u");
		return query.getResultList();
	}
	
	public Usuario buscar(String email, String senha) throws RepositoryException {
		
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT "); 
		jpql.append("  u ");
		jpql.append("FROM ");
		jpql.append("  Usuario u ");
		jpql.append("WHERE ");
		jpql.append("  u.email = :pEmail AND ");
		jpql.append("  u.senha = :pSenha AND ");
		jpql.append("  u.ativo = true ");
		
		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("pEmail", email);
		query.setParameter("pSenha", senha);
		
		try {
			return (Usuario)query.getSingleResult();
		} catch (NoResultException e) {
			throw new RepositoryException("Dados de usuário inválidos.");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public List<Usuario> filtrarPorNome(String nome) {
		try {
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append(" u ");
			jpql.append("FROM ");
			jpql.append(" Usuario u ");
			jpql.append("WHERE ");
			jpql.append(" LOWER(u.nome) LIKE LOWER(:nome)");

			Query query = getEntityManager().createQuery(jpql.toString());
		
			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();
		} catch (NoResultException error) {
			return null;
		}
	}

}
