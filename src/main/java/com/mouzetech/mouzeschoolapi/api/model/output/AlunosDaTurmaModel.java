package com.mouzetech.mouzeschoolapi.api.model.output;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunosDaTurmaModel {
	private String nomeTurma;
	private List<AlunoApenasNomeModel> alunos;
}