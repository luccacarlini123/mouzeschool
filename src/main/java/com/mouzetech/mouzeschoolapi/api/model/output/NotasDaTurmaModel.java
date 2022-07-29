package com.mouzetech.mouzeschoolapi.api.model.output;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class NotasDaTurmaModel {
	private String turma;
	List<RelacaoNotasDaTurmaModel> notas = new ArrayList<>();
}