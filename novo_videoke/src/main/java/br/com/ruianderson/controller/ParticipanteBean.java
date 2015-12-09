package br.com.ruianderson.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ruianderson.modelo.Participante;
import br.com.ruianderson.modelo.TipoUsuario;
import br.com.ruianderson.service.NegocioException;
import br.com.ruianderson.service.ParticipanteService;
import br.com.ruianderson.util.jsf.FacesUtil;


@Named
@ViewScoped
public class ParticipanteBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private ParticipanteService participanteService;
	
	private Participante participante;
		
	public String salvar() {
		try {
			participante.setTipousuario(TipoUsuario.PARTICIPANTE);
			
			Long verifica = new Long(0);
			if(participante.getId()== null)
			verifica = participanteService.verificaParticipanteCadastrado(participante);
			
			if(verifica == 0 || verifica == null){
				
				if(participante.getId() == null){
					if(participante.getSenha().trim().equals(participante.getConfimasenha().trim())){
						finalizaCadastro();
						return "homep.xhtml?faces-redirect=true";
					}else{
						FacesUtil.addErrorMessage("Senha e confimação de senha estão diferentes!");
					}
					
				}else{
					finalizaCadastro();
					return "";
				}
				
				
				
			}else{
				FacesUtil.addErrorMessage("E-mail e/ou Celular já cadastro no Sistema! Verifique os dados informados e tente novamente.");
				FacesUtil.addErrorMessage("E-mail:"+participante.getEmail() +" - "+ "celular: "+participante.getCelular());
			}
						
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		return "";
	}

	private void finalizaCadastro() throws NegocioException {
		this.participanteService.salvar(participante);
		FacesUtil.addSuccessMessage("Participante salvo com sucesso!");
		this.limpar();
	}
	
	@PostConstruct
	public void init() {
		this.limpar();
	}
	
	public void limpar() {
		this.participante = new Participante();
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	
	
}
