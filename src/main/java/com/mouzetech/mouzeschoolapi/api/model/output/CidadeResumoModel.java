package com.mouzetech.mouzeschoolapi.api.model.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeResumoModel {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Paracambi")
	private String nome;
	
	@ApiModelProperty(example = "Rio de Janeiro")
	private String estado;	
}