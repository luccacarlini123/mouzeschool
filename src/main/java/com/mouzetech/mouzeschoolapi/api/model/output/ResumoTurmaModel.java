package com.mouzetech.mouzeschoolapi.api.model.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ResumoTurmaModel {
	
	@ApiModelProperty(example = "3")
	private Long id;
	
	@ApiModelProperty(example = "Turma 3003")
	private String nome;
}