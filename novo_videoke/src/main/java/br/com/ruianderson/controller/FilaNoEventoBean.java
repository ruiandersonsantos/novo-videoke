package br.com.ruianderson.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ruianderson.modelo.FilaNoEvento;
import br.com.ruianderson.modelo.Participante;
import br.com.ruianderson.service.FilaNoEventoService;
import br.com.ruianderson.util.jsf.FacesUtil;


@Named
@ViewScoped
public class FilaNoEventoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FilaNoEventoService filanoeventoservice;
	
	private FilaNoEvento fila;
	
	private FilaNoEvento filaselecionada;
	
	private List<FilaNoEvento> listaParaCantar;
	
	public void alterarPosicao(){
		this.filanoeventoservice.adicionarNoEvento(filaselecionada);
		FacesUtil.addSuccessMessage("Posição do participante "+filaselecionada.getParticipante().getPrimeironome()+ " alterada com sucesso!");
		filaselecionada.setPosicao(null);
		filaselecionada.setCodigomusica(null);
	}
	
	public void cantou(FilaNoEvento objfila){
		
		if(!objfila.getParticipante().getPrimeiramusica().equals("")){
			
			objfila.setStatus(new Long(1));
			objfila.setHoracantada(Calendar.getInstance());
			
			this.filanoeventoservice.adicionarNoEvento(objfila);
					
			this.entrarNoEvento(objfila.getParticipante());
			
		}else{
			FacesUtil.addErrorMessage("Informe a proxima musica para "+objfila.getParticipante().getPrimeironome()+" cantar!");
		}
				
		
		
	}
	
	public void entrarNoEvento(Participante participante){
		
		boolean ok = false;
		ok = validaParticipante(participante);
		
		if(ok){
					
			fila.setPosicao(new Long(0));
			fila.setParticipante(participante);
			fila.setCodigomusica(participante.getPrimeiramusica());
			fila.setStatus(new Long(0));
			
			try {
				
				this.filanoeventoservice.adicionarNoEvento(fila);
				
				if(fila.getId()== null){
					FacesUtil.addSuccessMessage(fila.getParticipante().getPrimeironome()+ " adicionado a fila com sucesso!");
				}else{
					FacesUtil.addSuccessMessage(fila.getParticipante().getPrimeironome()+ " cantou e voltou para o final da fila!");
				}
				
			} catch (Exception e) {
				FacesUtil.addErrorMessage("Erro cocluindo a operação! "+e.getMessage());
			}
			
			
			atualizaListaParacantar();
			
		}
		
	
		
		
	}
	
	private boolean validaParticipante(Participante participante) {
		boolean retorno = true;
		
		if(participante.getPrimeiramusica().equals("")){
			FacesUtil.addErrorMessage("Informe o codigo da musica do participante "+participante.getPrimeironome()+"!");
			retorno = false;
		}
		
		if(this.filanoeventoservice.verificaParticipanteNoEvento(participante) && retorno){
			FacesUtil.addErrorMessage("Participante "+participante.getPrimeironome()+" já esta na fila e não pode ser adicionado novamente!");
			retorno = false;
		}
		
		
		return retorno;
	}

	@PostConstruct
	public void init() {
		//this.fila = new FilaNoEvento();
		this.atualizaListaParacantar();
		this.filaselecionada = new FilaNoEvento();
	}

	private void atualizaListaParacantar() {
		if(this.filanoeventoservice.buscarFila()!= null){
			this.listaParaCantar = this.filanoeventoservice.buscarFila();
		}else{
			this.listaParaCantar = new ArrayList<FilaNoEvento>();
		}
			
		this.fila = new FilaNoEvento();
		
	}

	public List<FilaNoEvento> getListaParaCantar() {
		return listaParaCantar;
	}

	public void setListaParaCantar(List<FilaNoEvento> listaParaCantar) {
		this.listaParaCantar = listaParaCantar;
	}

	public FilaNoEvento getFila() {
		return fila;
	}

	public void setFila(FilaNoEvento fila) {
		this.fila = fila;
	}

	public FilaNoEvento getFilaselecionada() {
		return filaselecionada;
	}

	public void setFilaselecionada(FilaNoEvento filaselecionada) {
		this.filaselecionada = filaselecionada;
	}

	
	
	
	
	
}
