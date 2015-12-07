package br.com.ruianderson.modelo;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class FilaNoEvento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long posicao;
	
	private String codigomusica;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar horacantada;
	
	@ManyToOne
	private Evento evento;
	
	@ManyToOne
	private Participante participante;
	
	private Long status;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigomusica() {
		return codigomusica;
	}
	public void setCodigomusica(String codigomusica) {
		this.codigomusica = codigomusica;
	}
	public Calendar getHoracantada() {
		return horacantada;
	}
	public void setHoracantada(Calendar horacantada) {
		this.horacantada = horacantada;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public Participante getParticipante() {
		return participante;
	}
	public void setParticipante(Participante participante) {
		this.participante = participante;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	
	
	
	public Long getPosicao() {
		return posicao;
	}
	public void setPosicao(Long posicao) {
		this.posicao = posicao;
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
		FilaNoEvento other = (FilaNoEvento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}
