package com.mouzetech.mouzeschoolapi.domain.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunosDaTurmaDTO {

	private String nomeTurma;
	private List<AlunoResumoDTO> alunos;
	

}