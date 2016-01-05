package br.com.ruianderson.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.mail.MessagingException;

import br.com.ruianderson.dao.ParticipanteDAO;
import br.com.ruianderson.email.EnviaEmail;
import br.com.ruianderson.modelo.Participante;
import br.com.ruianderson.util.jpa.Transactional;

public class ParticipanteService implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private ParticipanteDAO participanteDAO;
	
	@Inject
	private EnviaEmail objemail;

	@Transactional
	public void salvar(Participante participante) throws NegocioException {

		this.participanteDAO.salvar(participante);
		
		if(participante.getId() == null){
			try {
				objemail.enviarEmail(participante.getEmail(), participante.getPrimeironome());
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
