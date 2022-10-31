package com.mouzetech.mouzeschoolapi.api.model.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeModel {
	
	@ApiModelProperty(example = "Paracambi")
	private String nome;	
	
	@ApiModelProperty(value = "Representação de um estado", name = "corpo")
	private EstadoModel estado;
}