package com.mouzetech.mouzeschoolapi.api.model.output;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class NotasDaTurmaModel extends RepresentationModel<NotasDaTurmaModel> {
	
	@ApiModelProperty(example = "Turma 3001")
	private String turma;
	private List<RelacaoNotasDaTurmaModel> notas = new ArrayList<>();
}