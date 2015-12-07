package br.com.ruianderson.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.ruianderson.dao.EventoDAO;
import br.com.ruianderson.dao.FilaNoEventoDAO;
import br.com.ruianderson.dao.OrganizadorDAO;
import br.com.ruianderson.modelo.Evento;
import br.com.ruianderson.modelo.FilaNoEvento;
import br.com.ruianderson.modelo.Login;
import br.com.ruianderson.modelo.Participante;
import br.com.ruianderson.util.jpa.Transactional;

public class FilaNoEventoService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EventoDAO eventoDAO;
	
	@Inject
	private FilaNoEventoDAO filaDAO;
	
	private Evento evento;
	
	@SuppressWarnings("unused")
	@Transactional
	public void adicionarNoEvento(FilaNoEvento fila) {
		
		if(fila.getId()!=null){
			this.filaDAO.salvar(fila);
		}else{
			
			if(fila.getEvento() == null){
				
				this.pegarEventoPorOrganizador();
				
				fila.setEvento(this.evento);
				
			}
			
			Long ultimaPosicao = this.filaDAO.pegaUltimaPosicao(this.evento);
			
			// Se não for retornado valor do banco significa que é o primeiro da fila.
			if (ultimaPosicao == null){
				ultimaPosicao = new Long(1);
			}else{
				ultimaPosicao++;
			}
					
			
			fila.setPosicao(ultimaPosicao);
			
			this.filaDAO.salvar(fila);
			
		}
		
		
		
	}

	public void pegarEventoPorOrganizador() {
		// pegando o id do organizador
		Long id = retornaIdOrganizador();
		
		// pegando o evento ativo do organizador
		if(eventoDAO.buscarEventoAtivo(id).size()>0){
			this.evento = eventoDAO.buscarEventoAtivo(id).get(0);
		}else{
			this.evento = null;
		}
		
		
	}
	
	private Long retornaIdOrganizador() {
		Login login  = (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuariologado");
		Long id = login.getOrganizador().getId();
		return id;
	}

	public List<FilaNoEvento> buscarFila() {
		// Pegando o evento ativo conforme organizador logado
		// preenche a propriedade evento
		pegarEventoPorOrganizador();
		
		if(this.evento != null){
			return this.filaDAO.buscarFilaPorEvento(this.evento);
		}
		
		return null;
	}

	public boolean verificaParticipanteNoEvento(Participante participante) {

		return this.filaDAO.verificaParticipanteNoEvento(participante,this.evento);
		
		
	}

	public List<FilaNoEvento> buscarFilaParticipante(Evento evento2) {
		// TODO Auto-generated method stub
		return this.filaDAO.buscarFilaPorEvento(evento2);
	}

	public List<FilaNoEvento> buscarMusicasCantadas(Evento evento2) {
		return this.filaDAO.buscarMusicasCantadas(evento2);
	}

	
}
