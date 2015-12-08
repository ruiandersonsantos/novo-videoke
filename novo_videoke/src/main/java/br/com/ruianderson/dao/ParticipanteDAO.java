package br.com.ruianderson.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.ruianderson.modelo.Participante;


public class ParticipanteDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;
	
	public void salvar(Participante participante) {
		em.merge(participante);
	}
	
	@SuppressWarnings("unchecked")
	public List<Participante> buscarTodos() {
		return em.createQuery("from Participante").getResultList();
	}
	
	public Participante buscarPeloCodigo(Long codigo) {
		
		return em.find(Participante.class, codigo);
	
	}
	
	public Long vericifaParticipanteCadastrado(Participante participante){
		
		@SuppressWarnings("unchecked")
		List<Object> lista =  em.createQuery("select count(p) from Participante p where p.email = :email or p.celular = :celular")
				.setParameter("email", participante.getEmail())
				.setParameter("celular", participante.getCelular())
				.getResultList();
		
		return (Long) lista.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Participante> buscarParticipantes(String nome, String email,
			Integer celular) {
		return em.createQuery("select p from Participante p where p.primeironome like :nome and "
				+ "p.email like :email")
				.setParameter("nome", "%"+nome+"%")
				.setParameter("email", "%"+email+"%")
				.getResultList();
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Participante> buscarParticipantes(Integer celular) {
		return em.createQuery("select p from Participante p where p.celular = :celular")
				.setParameter("celular", celular)
				.getResultList();
		
	}

}
