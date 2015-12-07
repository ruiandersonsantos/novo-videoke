package br.com.ruianderson.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name="Musica", indexes={
		@Index(columnList="titulo",unique=false, name="idx_titulo"),
		@Index(columnList="cantor",unique=false, name="idx_cantor"),
		@Index(columnList="codigo",unique=false, name="idx_codigo"),
})
public class Musica {

	@Id
	private Long id;
	private String codigo;
	private String titulo;
	private String cantor;
	private String iniciomusica;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getCantor() {
		return cantor;
	}
	public void setCantor(String cantor) {
		this.cantor = cantor;
	}
	public String getIniciomusica() {
		return iniciomusica;
	}
	public void setIniciomusica(String iniciomusica) {
		this.iniciomusica = iniciomusica;
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
		Musica other = (Musica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
