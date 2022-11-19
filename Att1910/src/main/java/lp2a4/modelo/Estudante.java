package lp2a4.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Estudante
 *
 */
@Entity

public class Estudante implements Serializable {

	@Id
	@Column
	private String matricula;
	
	@Column(nullable=false)
	private String nome;

	private String email;
	
	private static final long serialVersionUID = 1L;

	public Estudante() {
		super();
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Estudante [matricula=" + matricula + ", nome=" + nome + ", email=" + email + "]";
	}
	
	
   
}
