package com.mouzetech.mouzeschoolapi.api.model.input;

import javax.validation.constraints.NotBlank;

import com.mouzetech.mouzeschoolapi.validation.CadastrarMateria;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter@CadastrarMateria
public class CadastrarMateriaInput {

	@NotBlank
	private String nome;	
	
	@NotBlank
	private String grauEnsino;
	
	@NotBlank
	private String serieEnsino;
	
}
