package com.mouzetech.mouzeschoolapi.api.model.input;

import javax.validation.constraints.NotBlank;

import com.mouzetech.mouzeschoolapi.validation.CadastrarTurma;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter@CadastrarTurma
public class CadastrarTurmaInput {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String grauEnsino;
	
	@NotBlank
	private String serieEnsino;
}
