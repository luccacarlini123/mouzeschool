package com.mouzetech.mouzeschoolapi.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInput {
	
	@ApiModelProperty(example = "Barra da Tijuca", required = true)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long estadoId;	
}