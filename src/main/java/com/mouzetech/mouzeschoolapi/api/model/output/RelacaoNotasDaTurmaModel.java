package com.mouzetech.mouzeschoolapi.api.model.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class RelacaoNotasDaTurmaModel {
	@ApiModelProperty(example = "Lucca")
	private String nomeAluno;
	
	@ApiModelProperty(example = "1")
	private Short bimestre;
	
	@ApiModelProperty(example = "Matemática")
	private String nomeMateria;
	
	@ApiModelProperty(example = "9.6")
	private Double valor;	
}