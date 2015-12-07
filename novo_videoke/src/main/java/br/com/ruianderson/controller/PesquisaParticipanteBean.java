package br.com.ruianderson.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ruianderson.dao.ParticipanteDAO;
import br.com.ruianderson.modelo.Participante;


@Named
@ViewScoped
public class PesquisaParticipanteBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	ParticipanteDAO participanteDAO;
	
	private List<Participante> participantes = new ArrayList<>();
	
	private Participante participanteSelecionado;
	
	public List<Participante> getParticipantes() {
		return participantes;
	}
	
	

	public Participante getParticipanteSelecionado() {
		return participanteSelecionado;
	}
	public void setParticipanteSelecionado(Participante participanteSelecionado) {
		this.participanteSelecionado = participanteSelecionado;
	}
	
	@PostConstruct
	public void inicializar() {
		participantes = participanteDAO.buscarTodos();
	}

}
