package br.com.ruianderson.service;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.ruianderson.dao.NoticiaDAO;
import br.com.ruianderson.modelo.Noticia;
import br.com.ruianderson.modelo.StatusNoticia;
import br.com.ruianderson.modelo.TipoNoticia;
import br.com.ruianderson.util.jpa.Transactional;
import br.com.ruianderson.util.jsf.FacesUtil;

public class NoticiaService implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private NoticiaDAO noticiaDao;

	@Transactional
	public Long salvar(Noticia noticia) {

		Long retorno = new Long(0);

		if (noticia.getDatafim().before(noticia.getDataInicio())) {

			FacesUtil
					.addErrorMessage("Data final não pode ser maior que a data inicial!");
			retorno = (long) 1;
			return retorno;

		}

		if (noticia.getStatusnoticia() == StatusNoticia.ATIVA) {
			retorno = noticiaDao.verificaDisponibilidade(noticia);
		}

		if (retorno == 0) {
			noticiaDao.salvar(noticia);
		} else {
			FacesUtil
					.addErrorMessage("Já existe noticia ATIVA com data posterior a de inicio para noticia "
							+ noticia.getTiponoticia() + "!");
		}

		return retorno;
	}

	public Noticia buscarNoticiaAtual(TipoNoticia tipo) {
		String dataAtual = getDateTime();
		return noticiaDao.buscarNoticiaAtual(tipo, dataAtual);
	}

	public List<Noticia> buscarTodas() {
		return noticiaDao.buscarTodas();
	}

	private String getDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		return dateFormat.format(c.getTime());
	}

}
