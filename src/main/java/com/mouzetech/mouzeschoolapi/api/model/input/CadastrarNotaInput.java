package com.mouzetech.mouzeschoolapi.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CadastrarNotaInput {
	
	@ApiModelProperty(example = "1")
	private Long alunoId;
	
	@ApiModelProperty(example = "1")
	private Long turmaId;
	
	@ApiModelProperty(example = "2")
	private Long materiaId;
	
	@ApiModelProperty(example = "9")
	private Double valor;
	
	@ApiModelProperty(example = "3")
	private Short bimestre;
}
