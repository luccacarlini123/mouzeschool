package com.mouzetech.mouzeschoolapi.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnvioEmailParaTodosInput {

	@ApiModelProperty(example = "Amanhã, dia 30/10/2022, vai ter o segundo turno das eleições.")
	@NotBlank
	private String textoCorpo;
	
	@ApiModelProperty(example = "AVISO IMPORTANTE!")
	@NotBlank
	private String assunto;
}