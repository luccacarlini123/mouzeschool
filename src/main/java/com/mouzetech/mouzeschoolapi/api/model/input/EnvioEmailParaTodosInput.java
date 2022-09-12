package com.mouzetech.mouzeschoolapi.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnvioEmailParaTodosInput {

	@NotBlank
	private String textoCorpo;
	
	@NotBlank
	private String assunto;
}