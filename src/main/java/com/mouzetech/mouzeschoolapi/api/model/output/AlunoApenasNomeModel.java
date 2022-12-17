package com.mouzetech.mouzeschoolapi.api.model.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoApenasNomeModel {
	@ApiModelProperty(example = "Lucca")
	private String nomeAluno;
}	