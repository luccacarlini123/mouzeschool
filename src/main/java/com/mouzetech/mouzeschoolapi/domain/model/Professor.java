package com.mouzetech.mouzeschoolapi.domain.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
@Data
public class Professor extends Pessoa {
	
	public Professor(Long id, String nome, String email){
		setId(id);
		setNome(nome);
		setEmail(email);
	}
	
	@ManyToOne
	@JoinColumn(name = "matricula_id", referencedColumnName = "id", nullable = false)
	private Matricula matricula;
	
	@Embedded
	private Endereco endereco;
}