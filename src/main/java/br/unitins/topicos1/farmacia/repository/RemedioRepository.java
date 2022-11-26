package br.unitins.topicos1.farmacia.repository;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.topicos1.farmacia.application.RepositoryException;
import br.unitins.topicos1.farmacia.model.Remedio;
import br.unitins.topicos1.farmacia.model.Usuario;

@ApplicationScoped
public class RemedioRepository extends Repository<Remedio> {

	public List<Remedio> buscarTodos() {
		try {
			Query query = getEntityManager().createQuery("SELECT r FROM Remedio r ORDER BY r.nome");
			return query.getResultList();
		} catch (Exception e) {
			return new ArrayList<Remedio>();
		}
	}

	public Remedio buscarPeloId(Integer id) throws RepositoryException {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("  r ");
		jpql.append("FROM ");
		jpql.append("  Remedio r ");
		jpql.append("WHERE ");
		jpql.append(" r.id = :id ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("id", id);

		try {
			// o metodo getSingleResult gera um eception quando nao existe um resultado
			return (Remedio) query.getSingleResult();
		} catch (NoResultException e) {
			throw new RepositoryException("Login ou senha Inválido");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<Remedio> buscarPeloNome(String nome) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("  r ");
		jpql.append("FROM ");
		jpql.append("  Remedio r ");
		jpql.append("WHERE ");
		jpql.append("  upper(r.nome) LIKE upper(:nome) ");
		jpql.append("ORDER BY r.nome ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("nome", "%" + nome + "%");

		return query.getResultList();

	}

}
