package com.mouzetech.mouzeschoolapi.api.model.input;

import lombok.Data;

@Data
public class CadastrarNotaInput {
	private Long alunoId;
	private Long turmaId;
	private Long materiaId;
	private Double valor;
	private Short bimestre;
}
