package br.com.ruianderson.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;



import br.com.ruianderson.dao.MusicaDAO;
import br.com.ruianderson.modelo.Musica;

public class MusicaService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Inject
	private MusicaDAO musicaDAO;
	
	public List<Musica> buscarMusica(String titulo, String cantor) {
		return musicaDAO.buscarMusica(titulo, cantor);
	}
	
	public List<Musica> buscarMusicaPorCodigo(String codigo) {
		return musicaDAO.buscarMusicaPorCodigo(codigo);
	}
}
