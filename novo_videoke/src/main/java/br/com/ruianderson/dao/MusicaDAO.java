package br.com.ruianderson.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.ruianderson.modelo.Evento;
import br.com.ruianderson.modelo.Musica;

public class MusicaDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;
	
	
	public List<Musica> buscarMusica(String titulo, String cantor) {

		List<Musica> obj = new ArrayList<Musica>();
		obj = em.createQuery(
				"select m from Musica m where m.titulo like :titulo and m.cantor like :cantor",
				Musica.class)
				.setParameter("titulo","%"+titulo+"%")
				.setParameter("cantor","%"+cantor+"%")
				.getResultList();

		return obj;

	}
	
	
	public List<Musica> buscarMusicaPorCodigo(String codigo) {

		List<Musica> obj = new ArrayList<Musica>();
		obj = em.createQuery(
				"select m from Musica m where m.codigo = :codigo",
				Musica.class)
				.setParameter("codigo",codigo)
				.getResultList();

		return obj;

	}
}
