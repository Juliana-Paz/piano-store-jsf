package br.unitins.topicos1.pianostore.repository;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.topicos1.pianostore.model.Marca;
import br.unitins.topicos1.pianostore.model.Usuario;

public class MarcaRepository extends Repository<Marca> {

	public Marca buscarPeloId(Integer id) {
		return getEntityManager().find(Marca.class, id);
	}

	public List<Marca> buscarTodos() {
		Query query = getEntityManager().createQuery("SELECT m FROM Marca m ORDER BY m.nome");
		return query.getResultList();
	}

	public List<Marca> filtrarPorNome(String nome) {
		try {
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append(" m ");
			jpql.append("FROM ");
			jpql.append(" Marca m ");
			jpql.append("WHERE ");
			jpql.append(" LOWER(m.nome) LIKE LOWER(:nome)");

			Query query = getEntityManager().createQuery(jpql.toString());

			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();
		} catch (NoResultException error) {
			return null;
		}
	}

}
