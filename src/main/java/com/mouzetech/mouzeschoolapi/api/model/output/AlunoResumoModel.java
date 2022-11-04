package com.mouzetech.mouzeschoolapi.api.model.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoResumoModel {
	@ApiModelProperty(example = "Lucca")
	private String nomeAluno;
}	