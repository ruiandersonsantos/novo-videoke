package br.com.ruianderson.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ruianderson.modelo.Evento;
import br.com.ruianderson.modelo.FilaNoEvento;
import br.com.ruianderson.modelo.Participante;
import br.com.ruianderson.service.FilaNoEventoService;

@Named
@ViewScoped
public class FilaParticipanteBean  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FilaNoEventoService filanoeventoservice;
	
	private List<FilaNoEvento> listaParaCantar;
	
	private List<FilaNoEvento> musicasCantadas;
	
	public void buscarFilaDoParticipante(Evento evento){
		musicasCantadas.clear();
		listaParaCantar = this.filanoeventoservice.buscarFilaParticipante(evento);
			
	}
	
	public void buscarMuscasCantadas(Evento evento){
		listaParaCantar.clear();
		musicasCantadas = this.filanoeventoservice.buscarMusicasCantadas(evento);
	}
	
	@PostConstruct
	public void init() {
		this.listaParaCantar = new ArrayList<FilaNoEvento>();
		this.musicasCantadas = new ArrayList<FilaNoEvento>();
	}

	public List<FilaNoEvento> getListaParaCantar() {
		return listaParaCantar;
	}

	public void setListaParaCantar(List<FilaNoEvento> listaParaCantar) {
		this.listaParaCantar = listaParaCantar;
	}

	public List<FilaNoEvento> getMusicasCantadas() {
		return musicasCantadas;
	}

	public void setMusicasCantadas(List<FilaNoEvento> musicasCantadas) {
		this.musicasCantadas = musicasCantadas;
	}
	
	
	
	
	
}
