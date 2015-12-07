package br.com.ruianderson.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.ruianderson.modelo.Evento;

public class EventoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;

	public void salvar(Evento evento) {
		em.merge(evento);
	}

	@SuppressWarnings("unchecked")
	public List<Evento> buscarTodos() {
		return em.createQuery("from Evento").getResultList();
	}

	public List<Evento> buscarEventoAtivo(Long idorganizador) {

		List<Evento> obj = new ArrayList<Evento>();
		obj = em.createQuery(
				"select e from Evento e where e.status = :status and e.organizador.id = :id",
				Evento.class).setParameter("status", new Long(0))
				.setParameter("id", idorganizador).getResultList();

		return obj;

	}

	public List<Evento> buscarEventoAtivoParticipante(Long idparticipante) {

		List<Evento> obj = new ArrayList<Evento>();
		obj = em.createQuery(
				"select f.evento from FilaNoEvento f where f.evento.status = :status and f.participante.id = :id "
				+ "and f.status = :statusfila",
				Evento.class)
				.setParameter("status", new Long(0))
				.setParameter("statusfila", new Long(0))
				.setParameter("id", idparticipante).getResultList();

		return obj;

	}

	public Evento buscarPeloCodigo(Long codigo) {

		return em.find(Evento.class, codigo);

	}

}
