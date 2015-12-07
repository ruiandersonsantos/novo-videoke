package br.com.ruianderson.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ruianderson.modelo.Participante;
import br.com.ruianderson.service.ParticipanteService;


@Named
@ViewScoped
public class LocalizarParticipanteBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String email;
	private Integer celular;
	private Participante participante;
	private List<Participante> listaparticipante;
	
	@Inject
	ParticipanteService participanteServive;
	
	public void localizarParticipante(){
		listaparticipante = participanteServive.buscaParticipantes(nome,email,celular);
	}
	
	public void limparPesquisa(){
		listaparticipante.clear();
	}
	
	
	@PostConstruct
	public void inicializar() {
		this.listaparticipante = new ArrayList<Participante>(); 
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getCelular() {
		return celular;
	}
	public void setCelular(Integer celular) {
		this.celular = celular;
	}
	public Participante getParticipante() {
		return participante;
	}
	public void setParticipante(Participante participante) {
		this.participante = participante;
	}
	public List<Participante> getListaparticipante() {
		return listaparticipante;
	}
	public void setListaparticipante(List<Participante> listaparticipante) {
		this.listaparticipante = listaparticipante;
	}
	
	
	
	
	
}
