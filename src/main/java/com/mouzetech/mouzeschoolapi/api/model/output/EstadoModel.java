package com.mouzetech.mouzeschoolapi.api.model.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoModel {
	
	@ApiModelProperty(example = "Rio de Janeiro")
	private String nome;
	
	@ApiModelProperty(example = "RJ")
	private String sigla;
}