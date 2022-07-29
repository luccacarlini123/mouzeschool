package com.mouzetech.mouzeschoolapi.api.model.output;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class RelacaoNotasDaTurmaModel {
	private String nomeAluno;
	private Short bimestre;
	private String nomeMateria;
	private Double valor;	
}