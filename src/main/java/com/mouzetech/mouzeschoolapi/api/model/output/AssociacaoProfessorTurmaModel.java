package com.mouzetech.mouzeschoolapi.api.model.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssociacaoProfessorTurmaModel {
	
	@ApiModelProperty(example = "Alberto Enrique")
	private String professor;
	
	@ApiModelProperty(example = "Portugês")
	private String materia;
}
