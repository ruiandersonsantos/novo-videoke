package br.com.ruianderson.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ruianderson.dao.ParticipanteDAO;
import br.com.ruianderson.modelo.Participante;
import br.com.ruianderson.util.cdi.CDIServiceLocator;


@FacesConverter(forClass = Participante.class)
public class ParticipanteConverter implements Converter{
	
	
	private ParticipanteDAO participanteDAO;
	
	public ParticipanteConverter(){
		this.participanteDAO = CDIServiceLocator.getBean(ParticipanteDAO.class);
	}
			
		
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Participante retorno = null;
		
		if (value != null) {
			retorno = this.participanteDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Participante) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}
