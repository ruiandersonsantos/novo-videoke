package br.com.ruianderson.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ruianderson.dao.OrganizadorDAO;
import br.com.ruianderson.modelo.Evento;
import br.com.ruianderson.modelo.Organizador;
import br.com.ruianderson.service.EventoService;
import br.com.ruianderson.service.NegocioException;
import br.com.ruianderson.util.jsf.FacesUtil;

@Named
@ViewScoped
public class EventoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EventoService eventoservice;
	private Evento evento;
	private List<Evento> eventos = new ArrayList<>();
	

	public String salvar() {
			
		try {
						
			this.eventoservice.salvar(evento);
			FacesUtil.addSuccessMessage("Foi aberto um novo evento com sucesso!");
			this.buscarEventosPorOrganizador();
			
			return "/restrito/Home.xhtml?faces-redirect=true";
			
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
			return "";
		}
	}
	
	public String fecharEvento(){
		
		this.eventos = eventoservice.buscarEventoAberto();
		
		try {
			Evento obj = eventos.get(0);
			obj.setDataFim(Calendar.getInstance());
			obj.setStatus(new Long(1));
			this.eventoservice.fechar(obj);
			this.buscarEventosPorOrganizador();
			
			return "/restrito/Home.xhtml?faces-redirect=true";
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage("Ocorreu um problema! Evento não foi fechado.");
			return "";
		}
		
		
	}
	
	@PostConstruct
	public void init() {
		this.limpar();
		this.buscarEventosPorOrganizador();
		
	}

	private void limpar() {
		this.evento = new Evento();
	}
	
	private void buscarEventosPorOrganizador(){
		this.eventos = eventoservice.buscarEventoAberto();
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

		
	

}
