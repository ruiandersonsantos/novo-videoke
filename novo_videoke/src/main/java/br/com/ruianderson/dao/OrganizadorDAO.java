package br.com.ruianderson.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import br.com.ruianderson.modelo.Organizador;


public class OrganizadorDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;
	
	public void salvar(Organizador organizador) {
		em.merge(organizador);
	}
	
	@SuppressWarnings("unchecked")
	public List<Organizador> buscarTodos() {
		return em.createQuery("from Organizador").getResultList();
	}
	
	public Organizador buscarPeloCodigo(Long codigo) {
		
		return em.find(Organizador.class, codigo);
	
	}

}
