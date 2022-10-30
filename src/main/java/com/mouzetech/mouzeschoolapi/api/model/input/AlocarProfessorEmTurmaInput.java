package com.mouzetech.mouzeschoolapi.api.model.input;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class AlocarProfessorEmTurmaInput {
	
	@ApiModelProperty(example = "1")
	Long professorId;
	
	@ApiModelProperty(example = "1")
	@NotNull
	Long turmaId;
	
	@ApiModelProperty(example = "2")
	@NotNull
	Long materiaId;
}