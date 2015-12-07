package br.com.ruianderson.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.ruianderson.modelo.Login;
import br.com.ruianderson.modelo.Organizador;
import br.com.ruianderson.modelo.Participante;
import br.com.ruianderson.service.NegocioException;
import br.com.ruianderson.util.jpa.Transactional;

public class LoginDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;
	
	public Participante autenticarParticipante(String emal, String senha) throws NegocioException{
		Participante obj = null;
		try {
			obj =  em.createQuery("select o from Participante o "
					+ "where o.email = :p_email and o.senha = :p_senha",Participante.class)
					.setParameter("p_email", emal)
					.setParameter("p_senha", senha)
					.getSingleResult();
		} catch (Exception e) {
			throw new NegocioException("Login e/ou senha incorreto!");
		}
		
		return obj;
		
	}
	
	public Organizador autenticar(String emal, String senha) throws NegocioException{
		Organizador obj = null;
		try {
			obj =  em.createQuery("select o from Organizador o "
					+ "where o.email = :p_email and o.senha = :p_senha",Organizador.class)
					.setParameter("p_email", emal)
					.setParameter("p_senha", senha)
					.getSingleResult();
		} catch (Exception e) {
			throw new NegocioException("Login e/ou senha incorreto!");
		}
		
		return obj;
		
	}
	
	@Transactional
	public void salvarLogin(Login login) {
		em.merge(login);
	}

	public Login buscarPorId(Long id) {
		return em.find(Login.class, id);
	}
}
