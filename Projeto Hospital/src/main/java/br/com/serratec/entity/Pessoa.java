package br.com.serratec.entity;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@MappedSuperclass
public abstract class Pessoa {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long codigo;
	
    @NotBlank(message = "O nome não pode estar em branco")
    protected String nome;
    
    @Email(message = "Email inválido.")
    protected String email;
    
    
    @NotBlank
    @Pattern(regexp = "^[0-9]{11}$", message = "Telefone deve conter apenas números e ter 11 dígitos")
    private String telefone;
    

    
    public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}

