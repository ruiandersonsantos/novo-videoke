package br.com.ruianderson.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;




import br.com.ruianderson.modelo.Musica;
import br.com.ruianderson.service.MusicaService;
import br.com.ruianderson.util.jsf.FacesUtil;

@Named
@ViewScoped
public class MusicaBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private MusicaService musicaservice;
	
	private List<Musica> musicas = new ArrayList<>();
	
	private String titulo;
	private String cantor;
	private String codigo;
	
	public void buscarMusica(){
		
		musicas.clear();
		
		if(!this.codigo.equals("")){
			this.musicas = musicaservice.buscarMusicaPorCodigo(codigo);
			if(musicas.size()==0){
				FacesUtil.addErrorMessage("Nenhuma musica encontrada!");
			}
		}else{
			
			if(titulo.equals("") && cantor.equals("")){
				FacesUtil.addErrorMessage("Preencha um dos campos!");
			}else{
				
				this.musicas = musicaservice.buscarMusica(titulo, cantor);
				if(musicas.size()==0){
					FacesUtil.addErrorMessage("Nenhuma musica encontrada!");
				}
			}
			
			
		}
		
		limpar();
	}
	
	@PostConstruct
	public void ini(){
		limpar();
	}
	
	public void limpar(){
		this.cantor = "";
		this.codigo = "";
		this.titulo = "";
	}

	

	public List<Musica> getMusicas() {
		return musicas;
	}



	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}



	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCantor() {
		return cantor;
	}

	public void setCantor(String cantor) {
		this.cantor = cantor;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
}
