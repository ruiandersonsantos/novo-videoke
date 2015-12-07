package br.com.ruianderson.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.ruianderson.modelo.Evento;
import br.com.ruianderson.modelo.FilaNoEvento;
import br.com.ruianderson.modelo.Participante;

public class FilaNoEventoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;

	public void salvar(FilaNoEvento fila) {

		em.merge(fila);

	}

	public List<FilaNoEvento> buscarFilaPorEvento(Evento evento) {

		return em
				.createQuery(
						"select f from FilaNoEvento f "
								+ "where f.evento.id = :evento and f.status = 0 order by f.posicao asc",
						FilaNoEvento.class)
				.setParameter("evento", evento.getId()).getResultList();
	}

	public Long pegaUltimaPosicao(Evento evento) {

		List<Long> obj = em
				.createQuery(
						"select f.posicao from FilaNoEvento f "
								+ "where f.evento.id = :evento and f.status = 0 order by f.posicao desc",
						Long.class).setParameter("evento", evento.getId())
				.getResultList();

		if (obj.size() > 0) {
			return obj.get(0);
		}

		return null;
	}

	public boolean verificaParticipanteNoEvento(Participante participante,
			Evento evento) {
		
		List<Long> obj = em
				.createQuery(
						"select f.id from FilaNoEvento f "
								+ "where f.participante.id = :participante "
								+ "and f.evento.id = :evento and f.status = 0",
						Long.class)
						.setParameter("evento", evento.getId())
						.setParameter("participante", participante.getId())
				.getResultList();
		
		if(obj.size()>0){
			return true;
		}
		
		return false;
		
	}

	public FilaNoEvento buscarPeloCodigo(Long codigo) {
		return em.find(FilaNoEvento.class, codigo);
	}

	public List<FilaNoEvento> buscarMusicasCantadas(Evento evento) {
		return em
				.createQuery(
						"select f from FilaNoEvento f "
								+ "where f.evento.id = :evento and f.status = 1 order by f.horacantada desc",
						FilaNoEvento.class)
				.setParameter("evento", evento.getId()).getResultList();
	}

	
}
