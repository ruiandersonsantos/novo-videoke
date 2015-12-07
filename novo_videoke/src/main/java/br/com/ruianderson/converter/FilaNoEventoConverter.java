package br.com.ruianderson.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ruianderson.dao.FilaNoEventoDAO;
import br.com.ruianderson.modelo.FilaNoEvento;

import br.com.ruianderson.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = FilaNoEvento.class)
public class FilaNoEventoConverter implements Converter{
	
	private FilaNoEventoDAO filanoeventoDAO;
	
	public FilaNoEventoConverter() {
		this.filanoeventoDAO = CDIServiceLocator.getBean(FilaNoEventoDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		FilaNoEvento retorno = null;
		
		if (value != null) {
			retorno = this.filanoeventoDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
		
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Long codigo = ((FilaNoEvento) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}
