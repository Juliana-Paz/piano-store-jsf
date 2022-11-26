package br.unitins.topicos1.farmacia.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

import br.unitins.topicos1.farmacia.model.Compra;
import br.unitins.topicos1.farmacia.model.Usuario;

@ApplicationScoped
public class CompraRepository extends Repository<Compra> {

	public List<Compra> buscarTodos() {
		Query query = getEntityManager().createQuery("SELECT c FROM Compra c ORDER BY c.id DESC");
		return query.getResultList();
	}
	
	public List<Compra> buscarTodos(Usuario usuario) {
		StringBuffer jpql = new StringBuffer();
		
		jpql.append("SELECT ");
		jpql.append("  c ");
		jpql.append("FROM ");
		jpql.append("  Compra c ");
		jpql.append("WHERE ");
		jpql.append("  c.usuario.id = :idUsuario ");
		jpql.append("ORDER BY c.id DESC ");
		
		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("idUsuario", usuario.getId());
		
		return query.getResultList();
	}
	
}
