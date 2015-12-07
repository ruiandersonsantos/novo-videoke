package br.com.ruianderson.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.ruianderson.dao.EventoDAO;
import br.com.ruianderson.modelo.Evento;
import br.com.ruianderson.util.cdi.CDIServiceLocator;


@FacesConverter(forClass = Evento.class)
public class EventoConverter implements Converter{
	
	private EventoDAO eventoDAO;
	
	public EventoConverter(){
		this.eventoDAO = CDIServiceLocator.getBean(EventoDAO.class);
	}
		
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Evento retorno = null;
		
		if (value != null) {
			retorno = this.eventoDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Evento) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}
