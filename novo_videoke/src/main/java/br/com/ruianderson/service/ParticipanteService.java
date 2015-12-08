package br.com.ruianderson.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.ruianderson.dao.ParticipanteDAO;
import br.com.ruianderson.modelo.Participante;
import br.com.ruianderson.util.jpa.Transactional;

public class ParticipanteService implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private ParticipanteDAO participanteDAO;

	@Transactional
	public void salvar(Participante participante) throws NegocioException {

		this.participanteDAO.salvar(participante);
	}
	
	public Long verificaParticipanteCadastrado(Participante participante){
		return participanteDAO.vericifaParticipanteCadastrado(participante);
	}

	public List<Participante> buscaParticipantes(String nome, String email,
			Integer celular) {
		
		if (celular == null) {
			return this.participanteDAO.buscarParticipantes(nome, email,
					celular);
		}

		return this.participanteDAO.buscarParticipantes(celular);
	}

}
