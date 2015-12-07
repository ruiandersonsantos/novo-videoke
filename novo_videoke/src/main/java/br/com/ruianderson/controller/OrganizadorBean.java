package br.com.ruianderson.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ruianderson.modelo.Organizador;
import br.com.ruianderson.modelo.TipoUsuario;
import br.com.ruianderson.service.NegocioException;
import br.com.ruianderson.service.OrganizadorService;
import br.com.ruianderson.util.jsf.FacesUtil;

@Named
@ViewScoped
public class OrganizadorBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private OrganizadorService organizadorService;
	
	private Organizador organizador;
	
	public void salvar() {
		try {
			organizador.setTipousuario(TipoUsuario.ORGANIZADOR);
			this.organizadorService.salvar(organizador);
			FacesUtil.addSuccessMessage("Organizador salvo com sucesso!");
			
			this.limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	@PostConstruct
	public void init() {
		this.limpar();
	}
	
	public void limpar() {
		this.organizador = new Organizador();
	}

	public Organizador getOrganizador() {
		return organizador;
	}

	public void setOrganizador(Organizador organizador) {
		this.organizador = organizador;
	}
	
	

}
