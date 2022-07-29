package com.mouzetech.mouzeschoolapi.api.model.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotaFilter {
	private String materia;
	private Short bimestre;
	private String aluno;
}