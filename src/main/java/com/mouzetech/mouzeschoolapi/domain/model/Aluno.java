package com.mouzetech.mouzeschoolapi.domain.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Data
public class Aluno extends Pessoa {
	
	@ManyToOne
	@JoinColumn(name = "matricula_id", referencedColumnName = "id", nullable = false)
	private Matricula matricula;
	
	@Embedded
	private Endereco endereco;
}