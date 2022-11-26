package br.unitins.topicos1.farmacia.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.topicos1.farmacia.model.Estado;

public class EstadoRepository extends Repository<Estado> {

	public Estado buscarPeloId(Integer id) {
		return getEntityManager().find(Estado.class, id);
	}

	public List<Estado> buscarTodos() {
		Query query = getEntityManager().createQuery("SELECT e FROM Estado e ORDER BY e.nome");
		return query.getResultList();
	}

}
