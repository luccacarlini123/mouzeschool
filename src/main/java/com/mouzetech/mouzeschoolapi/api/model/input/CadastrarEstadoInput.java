package com.mouzetech.mouzeschoolapi.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CadastrarEstadoInput {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String sigla;	
}