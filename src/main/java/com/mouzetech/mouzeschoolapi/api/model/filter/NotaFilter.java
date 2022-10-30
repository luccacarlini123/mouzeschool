package com.mouzetech.mouzeschoolapi.api.model.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotaFilter {
	
	@ApiModelProperty(example = "a", value = "Aceita um pedaço do nome da matéria")
	private String materia;
	
	@ApiModelProperty(example = "1", value = "O valor do bimestre vai de 1 a 4")
	private Short bimestre;
	
	@ApiModelProperty(example = "a", value = "Aceita um pedaço do nome do aluno")
	private String aluno;
}