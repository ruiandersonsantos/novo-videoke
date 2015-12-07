package br.com.ruianderson.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ruianderson.dao.OrganizadorDAO;
import br.com.ruianderson.modelo.Organizador;
import br.com.ruianderson.util.cdi.CDIServiceLocator;


@FacesConverter(forClass = Organizador.class)
public class OrganizadorConverter implements Converter{
	
	
	private OrganizadorDAO organizadorDAO;
	
	public OrganizadorConverter(){
		this.organizadorDAO = CDIServiceLocator.getBean(OrganizadorDAO.class);
	}
			
		
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Organizador retorno = null;
		
		if (value != null) {
			retorno = this.organizadorDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Organizador) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}
