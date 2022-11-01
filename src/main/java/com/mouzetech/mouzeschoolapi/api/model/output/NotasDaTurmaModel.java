package com.mouzetech.mouzeschoolapi.api.model.output;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class NotasDaTurmaModel {
	
	@ApiModelProperty(example = "Turma 3001")
	private String turma;
	List<RelacaoNotasDaTurmaModel> notas = new ArrayList<>();
}