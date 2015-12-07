package br.com.ruianderson.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.common.collect.SetMultimap;

import br.com.ruianderson.modelo.Noticia;
import br.com.ruianderson.modelo.TipoNoticia;

public class NoticiaDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;
	
	public void salvar(Noticia noticia){
		em.merge(noticia);
	}
	
	@SuppressWarnings("unchecked")
	public List<Noticia> buscarTodas(){
		return em.createQuery("from Noticia").getResultList();
	}

	public Noticia buscarPeloCodigo(Long long1) {
		return em.find(Noticia.class, long1);
	}
	
	
	public Long verificaDisponibilidade(Noticia noticia){
		
		Long total = new Long(0);
		
		total = (Long) em.createQuery("select count(n.id) from Noticia n where n.datafim >= :datainicio and n.tiponoticia = :tiponoticia and n.statusnoticia = 0 "
				+ "and n.id != :id")
				.setParameter("datainicio", noticia.getDataInicio())
				.setParameter("tiponoticia", noticia.getTiponoticia())
				.setParameter("id", noticia.getId())
				.getSingleResult();
		
		return total;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public Noticia buscarNoticiaAtual(TipoNoticia tipo, String dataAual) {
		
		List<Noticia> list = new ArrayList<Noticia>();
		
		String jpql = "select n from Noticia n "
				+ " where n.statusnoticia = 0 and "
				+ "n.tiponoticia = :tiponoticia and  "
				+ "n.datafim >= :dataAtual order by id asc";
		
		list =  em.createQuery(jpql)
				.setParameter("tiponoticia", tipo)
				.setParameter("dataAtual", new Date(dataAual))
				.setMaxResults(1)
				.getResultList();
		
		if(list.size() != 0)
		return list.get(0);
		
		return null;
	}
}
