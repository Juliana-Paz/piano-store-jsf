package br.unitins.topicos1.pianostore.repository;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.topicos1.pianostore.application.RepositoryException;
import br.unitins.topicos1.pianostore.model.Instrumento;

@ApplicationScoped
public class InstrumentoRepository extends Repository<Instrumento> {

	public List<Instrumento> buscarTodos() {
		try {
			Query query = getEntityManager().createQuery("SELECT i FROM Instrumento i ORDER BY i.nome");
			return query.getResultList();
		} catch (Exception e) {
			return new ArrayList<Instrumento>();
		}
	}

	public Instrumento buscarPeloId(Integer id) throws RepositoryException {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("  i ");
		jpql.append("FROM ");
		jpql.append("  Instrumento i ");
		jpql.append("WHERE ");
		jpql.append(" i.id = :id ");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("id", id);

		try {
			// o metodo getSingleResult gera um eception quando nao existe um resultado
			return (Instrumento) query.getSingleResult();
		} catch (NoResultException e) {
			throw new RepositoryException("Instrumento não encontrado");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<Instrumento> filtrarPorNome(String nome) {
		try {
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append(" i ");
			jpql.append("FROM ");
			jpql.append(" Instrumento i ");
			jpql.append("WHERE ");
			jpql.append(" LOWER(i.nome) LIKE LOWER(:nome)");

			Query query = getEntityManager().createQuery(jpql.toString());

			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();
		} catch (NoResultException error) {
			return null;
		}
	}

}
