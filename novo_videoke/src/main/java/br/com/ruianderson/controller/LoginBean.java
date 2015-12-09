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
import br.com.ruianderson.service.ParticipanteService;
import br.com.ruianderson.util.cdi.CDIServiceLocator;
import br.com.ruianderson.util.jsf.FacesUtil;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private LoginService loginservice;
	private ParticipanteService participanteservice;
	
	private Login login;
	
	private String senha;
	private String novasenha;
	private String confirmasenha;
	
	public LoginBean(){
		this.loginservice = CDIServiceLocator.getBean(LoginService.class);
		this.participanteservice = CDIServiceLocator.getBean(ParticipanteService.class);
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
	
	public String alterarSenhaParticipante() {
		// pegando o objeto login da sessão.
		Login objlogin = (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuariologado"); 
		
		if(objlogin != null){
			//Subscrevo a senha do objeto pela senha digitada 
			objlogin.setSenha(senha);
			
			try {
				Participante objparticipante = this.loginservice.autenticarParticipante(objlogin);
				if(objparticipante != null){
					
					if(novasenha.trim().equals(confirmasenha.trim())){
						objparticipante.setSenha(novasenha);
						
						this.participanteservice.salvar(objparticipante);
						
						FacesUtil.addSuccessMessage("Nova senha cadastrada com sucesso!");
						
						this.confirmasenha = "";
						this.novasenha = "";
						this.senha = "";
					}
					
				}else{
					FacesUtil.addErrorMessage("Senha atual não confere!");
				}
				
			} catch (NegocioException e) {
				// TODO Auto-generated catch block
				FacesUtil.addErrorMessage("Senha atual não confere!");
			}
			
		}
		
		
		return "";
		
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
		
		String retorno = "";
		
		Login login = (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuariologado");
		this.loginservice.sairSistema();
		FacesUtil.addSuccessMessage("Obrigado por usar nosso sistema!");
		if(login.getParticipante() == null){
			retorno = "/index.xhtml?faces-redirect=true";
		}else{
			
			retorno = "/mobile/loginp2.xhtml?faces-redirect=true";
		}
		
		
		
		this.limpar();
		
		return retorno;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNovasenha() {
		return novasenha;
	}

	public void setNovasenha(String novasenha) {
		this.novasenha = novasenha;
	}

	public String getConfirmasenha() {
		return confirmasenha;
	}

	public void setConfirmasenha(String confirmasenha) {
		this.confirmasenha = confirmasenha;
	}
	
	
}
