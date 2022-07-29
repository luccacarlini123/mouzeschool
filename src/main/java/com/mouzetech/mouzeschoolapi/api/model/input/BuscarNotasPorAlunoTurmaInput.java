package com.mouzetech.mouzeschoolapi.api.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class BuscarNotasPorAlunoTurmaInput {
	private Long alunoId;
	private Long turmaId;
}