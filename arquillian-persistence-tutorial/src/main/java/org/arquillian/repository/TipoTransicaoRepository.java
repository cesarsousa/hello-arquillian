package org.arquillian.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.arquillian.example.TipoTransicao;



public class TipoTransicaoRepository extends GenericDao<TipoTransicao, Long> {

	@PersistenceContext
	private EntityManager em;
	
	public TipoTransicao obterPorDescricao(String descricao) {
		
		
		return (TipoTransicao) em.createQuery("SELECT c FROM TipoTransicao c WHERE c.descricaoTipoTransicao LIKE :custName")
				.setParameter("custName", descricao).getSingleResult();
				
		
	}

}