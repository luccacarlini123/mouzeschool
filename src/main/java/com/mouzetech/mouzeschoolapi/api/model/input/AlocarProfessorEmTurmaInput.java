package com.mouzetech.mouzeschoolapi.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class AlocarProfessorEmTurmaInput {
	
	Long professorId;
	
	@NotNull
	Long turmaId;
	
	@NotNull
	Long materiaId;
}