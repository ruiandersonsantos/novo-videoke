package br.com.ruianderson.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ruianderson.modelo.Evento;
import br.com.ruianderson.service.EventoService;

@Named
@ViewScoped
public class EventoBeanParticipante implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EventoService eventoservice;
	
	private List<Evento> eventosParticipante = new ArrayList<>();
	
	
	private void buscarEventosParticipante(){
		this.eventosParticipante = eventoservice.buscarEventoParticipante();
	}
	
	

	@PostConstruct
	public void init() {
		this.buscarEventosParticipante();
		
	}

	
	public List<Evento> getEventosParticipante() {
		return eventosParticipante;
	}


	public void setEventosParticipante(List<Evento> eventosParticipante) {
		this.eventosParticipante = eventosParticipante;
	}
	

}
