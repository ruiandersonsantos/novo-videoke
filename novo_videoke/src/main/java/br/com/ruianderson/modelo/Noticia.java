package br.com.ruianderson.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Noticia", indexes={
		@Index(columnList="datafim",unique=false, name="idx_datafim"),
		@Index(columnList="tiponoticia",unique=false, name="idx_tiponoticia"),
		@Index(columnList="statusnoticia",unique=false, name="idx_statusnoticia"),
		@Index(columnList="dataInicio",unique=false, name="idx_dataInicio"),
		@Index(columnList="titulo",unique=false, name="idx_titulo"),
		@Index(columnList="textofoto",unique=false, name="idx_textofoto")
})
public class Noticia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	
	private String textochamada;
	@Lob
	private String textodanoticia;
	private String responsavel;
	private String sitenoticia;
	private String linkfoto;
	private String textofoto;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicio;
	@Temporal(TemporalType.TIMESTAMP)
	private Date datafim;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoNoticia tiponoticia;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusNoticia statusnoticia;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTextochamada() {
		return textochamada;
	}
	public void setTextochamada(String textochamada) {
		this.textochamada = textochamada;
	}
	public String getTextodanoticia() {
		return textodanoticia;
	}
	public void setTextodanoticia(String textodanoticia) {
		this.textodanoticia = textodanoticia;
	}
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	public String getSitenoticia() {
		return sitenoticia;
	}
	public void setSitenoticia(String sitenoticia) {
		this.sitenoticia = sitenoticia;
	}
	
	public TipoNoticia getTiponoticia() {
		return tiponoticia;
	}
	public void setTiponoticia(TipoNoticia tiponoticia) {
		this.tiponoticia = tiponoticia;
	}
	
	
	
	
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDatafim() {
		return datafim;
	}
	public void setDatafim(Date datafim) {
		this.datafim = datafim;
	}
	public StatusNoticia getStatusnoticia() {
		return statusnoticia;
	}
	public void setStatusnoticia(StatusNoticia statusnoticia) {
		this.statusnoticia = statusnoticia;
	}
	public String getLinkfoto() {
		return linkfoto;
	}
	public void setLinkfoto(String linkfoto) {
		this.linkfoto = linkfoto;
	}
	
	
	
	
	public String getTextofoto() {
		return textofoto;
	}
	public void setTextofoto(String textofoto) {
		this.textofoto = textofoto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Noticia other = (Noticia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	

}
