package br.com.ruianderson.service;

import java.io.Serializable;
import java.util.Calendar;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.ruianderson.dao.LoginDAO;
import br.com.ruianderson.modelo.Login;
import br.com.ruianderson.modelo.Organizador;
import br.com.ruianderson.modelo.Participante;

public class LoginService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Inject
	private LoginDAO loginDAO;
	
	
	
	public void autenticarOrganizador(Login login) throws NegocioException{
		Organizador organizador = null;
		organizador = loginDAO.autenticar(login.getEmail().trim(), login.getSenha().trim());
		
		if(organizador == null){
			throw new NegocioException("Login e/ou senha incorreto!");
		}
		login.setOrganizador(organizador);
		login.setEntradaNoSistema(Calendar.getInstance());
		
		
		loginDAO.salvarLogin(login);
		
	}
	
	
	public Participante autenticarParticipante(Login login) throws NegocioException{
		Participante participante = null;
		participante = loginDAO.autenticarParticipante(login.getEmail(), login.getSenha());
		
		if(participante == null){
			login.setParticipante(participante);
			throw new NegocioException("Login e/ou senha incorreto!");
		}else{
			login.setEntradaNoSistema(Calendar.getInstance());
			
			
			loginDAO.salvarLogin(login);
			
			return participante;
		}
		
		
		
	}



	public void sairSistema() {
		// Retirando usuario logado da sessão
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuariologado", null);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
					
	}
}
