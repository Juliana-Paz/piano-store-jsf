package br.unitins.topicos1.pianostore.repository;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.topicos1.pianostore.model.SubTipo;

public class SubTipoRepository extends Repository<SubTipo> {

	public SubTipo buscarPeloId(Integer id) {
		return getEntityManager().find(SubTipo.class, id);
	}

	public List<SubTipo> buscarTodos() {
		Query query = getEntityManager().createQuery("SELECT st FROM SubTipo st ORDER BY st.tipo");
		return query.getResultList();
	}

	public List<SubTipo> filtrarPorNome(String nome) {
		try {
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append(" st ");
			jpql.append("FROM ");
			jpql.append(" SubTipo st ");
			jpql.append("WHERE ");
			jpql.append(" LOWER(st.nome) LIKE LOWER(:nome)");

			Query query = getEntityManager().createQuery(jpql.toString());

			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();
		} catch (NoResultException error) {
			return null;
		}
	}

}
