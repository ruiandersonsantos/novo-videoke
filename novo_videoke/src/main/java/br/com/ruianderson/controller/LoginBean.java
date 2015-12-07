package br.com.ruianderson.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ruianderson.dao.EventoDAO;
import br.com.ruianderson.modelo.Evento;
import br.com.ruianderson.modelo.Login;
import br.com.ruianderson.modelo.Participante;
import br.com.ruianderson.service.LoginService;
import br.com.ruianderson.service.NegocioException;
import br.com.ruianderson.util.cdi.CDIServiceLocator;
import br.com.ruianderson.util.jsf.FacesUtil;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private LoginService loginservice;
	
	private Login login;
	
	public LoginBean(){
		this.loginservice = CDIServiceLocator.getBean(LoginService.class);
	}
	
	public String logar(){
		try {
						
			this.loginservice.autenticarOrganizador(login);
			// Colocando usuario logado na sessão
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuariologado", login);
			
			FacesUtil.addSuccessMessage("Organizador logado com sucesso!");
			return "/restrito/Home.xhtml?faces-redirect=true";
			
			
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
			return null;
		}
	}
	
	public String logarParticipante(){
		try {
						
			Participante obj = this.loginservice.autenticarParticipante(login);
			login.setParticipante(obj);
			// Colocando usuario logado na sessão
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuariologado", login);
			
			//FacesUtil.addSuccessMessage("Usuario logado com sucesso!");
			return "/mobile/homep.xhtml?faces-redirect=true";
			
			
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
			return null;
		}
	}
	
	public String sair(){
		Login login = (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuariologado");
		this.loginservice.sairSistema();
		
		FacesUtil.addSuccessMessage("Obrigado por usr nosso sistema!");
		
		this.limpar();
		
		return "/index.xhtml?faces-redirect=true";
	}
	
	
	
	@PostConstruct
	public void init() {
		this.limpar();
	}

	private void limpar() {
		this.login = new Login();	
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
}
