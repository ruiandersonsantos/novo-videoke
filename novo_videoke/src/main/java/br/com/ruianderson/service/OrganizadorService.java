package br.com.ruianderson.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.ruianderson.dao.OrganizadorDAO;
import br.com.ruianderson.dao.ParticipanteDAO;
import br.com.ruianderson.modelo.Organizador;
import br.com.ruianderson.modelo.Participante;
import br.com.ruianderson.util.jpa.Transactional;



public class OrganizadorService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Inject
	private OrganizadorDAO organizadorDAO;
	
	@Transactional
	public void salvar(Organizador organizador) throws NegocioException {
			
		this.organizadorDAO.salvar(organizador);
	}

}
