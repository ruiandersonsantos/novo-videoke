package br.com.ruianderson.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;




import br.com.ruianderson.modelo.Noticia;
import br.com.ruianderson.modelo.StatusNoticia;
import br.com.ruianderson.modelo.TipoNoticia;
import br.com.ruianderson.service.NoticiaService;
import br.com.ruianderson.util.jsf.FacesUtil;

@Named
@ViewScoped
public class NoticiaBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private NoticiaService noticiaService;
	
	private Noticia noticia;
	private List<TipoNoticia> tipoNoticias;
	private List<StatusNoticia> statusNoticias;
	private List<Noticia> listaNoticias = new ArrayList<Noticia>();
	
	private Noticia noticiaprincipal;
	private Noticia noticia1;
	private Noticia noticia2;
	private Noticia noticia3;
	
	@PostConstruct
	public void init() {
		this.limpar();
		this.tipoNoticias =  Arrays.asList(TipoNoticia.values());
		this.statusNoticias =  Arrays.asList(StatusNoticia.values());
		this.listaNoticias = noticiaService.buscarTodas();
		this.carregaNoticias();
	}
	
	private void carregaNoticias(){
		
		this.noticiaprincipal = buscarNoticiaAtual(TipoNoticia.PRINCIPAL);
		this.noticia1 = buscarNoticiaAtual(TipoNoticia.NOTICIA_1);
		this.noticia2 = buscarNoticiaAtual(TipoNoticia.NOTICIA_2);
		this.noticia3 = buscarNoticiaAtual(TipoNoticia.NOTICIA_3);
		
	}
	
	public void limpar() {
		this.noticia = new Noticia();
	}
	
	
	public void salvar(){
		Long ok = noticiaService.salvar(noticia);
		if(ok ==0){
			this.limpar();
			FacesUtil.addSuccessMessage("Noticia salva com sucesso!");
		}
		
	}
	
	@SuppressWarnings("unused")
	private Noticia buscarNoticiaAtual(TipoNoticia tipo){
		return noticiaService.buscarNoticiaAtual(tipo);
	}
	

	public Noticia getNoticia() {
		return noticia;
	}

	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}

	public List<TipoNoticia> getTipoNoticias() {
		return tipoNoticias;
	}

	public void setTipoNoticias(List<TipoNoticia> tipoNoticias) {
		this.tipoNoticias = tipoNoticias;
	}

	public List<StatusNoticia> getStatusNoticias() {
		return statusNoticias;
	}

	public void setStatusNoticias(List<StatusNoticia> statusNoticias) {
		this.statusNoticias = statusNoticias;
	}

	public List<Noticia> getListaNoticias() {
		return listaNoticias;
	}

	public void setListaNoticias(List<Noticia> listaNoticias) {
		this.listaNoticias = listaNoticias;
	}
	
	public Noticia getNoticiaprincipal() {
		return noticiaprincipal;
	}

	public void setNoticiaprincipal(Noticia noticiaprincipal) {
		this.noticiaprincipal = noticiaprincipal;
	}

	public Noticia getNoticia1() {
		return noticia1;
	}

	public void setNoticia1(Noticia noticia1) {
		this.noticia1 = noticia1;
	}

	public Noticia getNoticia2() {
		return noticia2;
	}

	public void setNoticia2(Noticia noticia2) {
		this.noticia2 = noticia2;
	}

	public Noticia getNoticia3() {
		return noticia3;
	}

	public void setNoticia3(Noticia noticia3) {
		this.noticia3 = noticia3;
	}
	
	
}
