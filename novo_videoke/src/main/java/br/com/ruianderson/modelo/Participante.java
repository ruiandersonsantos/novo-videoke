package br.com.ruianderson.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="Participante", indexes={
		@Index(columnList="email",unique=true, name="idx_email"),
		@Index(columnList="senha",unique=false, name="idx_senha"),
		@Index(columnList="dataNascimento",unique=false, name="idx_dtNasc"),
		@Index(columnList="primeironome",unique=false, name="idx_nome"),
		@Index(columnList="tipousuario",unique=false, name="idx_tipo"),
		@Index(columnList="celular",unique=false, name="idx_celular")
		
})
public class Participante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String primeironome;
	private String email;
	private Integer celular;
	private String senha;
	private Date dataNascimento;
	
	@Transient
	private String confimasenha;
	
	@Transient
	private String primeiramusica;
	
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipousuario;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@NotBlank(message = "Nome deve ser preenchido")
	public String getPrimeironome() {
		return primeironome;
	}
	public void setPrimeironome(String primeironome) {
		this.primeironome = primeironome;
	}
	
	@NotBlank(message = "E-mail deve ser preenchido!")
	@Email(message = "Digite um e-mail valido!")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@NotBlank(message = "senha deve ser preenchida!")
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@NotNull(message = "Data de nascimento deve ser preenchida!")
	@Temporal(TemporalType.DATE)
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	
	@NotNull(message="Celular deve ser preenchido!")
	public Integer getCelular() {
		return celular;
	}
	public void setCelular(Integer celular) {
		this.celular = celular;
	}
		
	
	public TipoUsuario getTipousuario() {
		return tipousuario;
	}
	public void setTipousuario(TipoUsuario tipousuario) {
		this.tipousuario = tipousuario;
	}
	public String getPrimeiramusica() {
		return primeiramusica;
	}
	public void setPrimeiramusica(String primeiramusica) {
		this.primeiramusica = primeiramusica;
	}
	
	
	
	
	public String getConfimasenha() {
		return confimasenha;
	}
	public void setConfimasenha(String confimasenha) {
		this.confimasenha = confimasenha;
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
		Participante other = (Participante) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
