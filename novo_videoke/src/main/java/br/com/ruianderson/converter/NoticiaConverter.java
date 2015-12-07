package br.com.ruianderson.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ruianderson.dao.NoticiaDAO;
import br.com.ruianderson.modelo.Evento;
import br.com.ruianderson.modelo.Noticia;
import br.com.ruianderson.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Noticia.class)
public class NoticiaConverter implements Converter{
	
	private NoticiaDAO noticiadao;
	
	public NoticiaConverter() {
		this.noticiadao = CDIServiceLocator.getBean(NoticiaDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		Noticia retorno = null;
		
		if (value != null) {
			retorno = this.noticiadao.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Long codigo = ((Noticia) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}
