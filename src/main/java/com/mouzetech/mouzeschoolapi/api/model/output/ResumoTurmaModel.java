package com.mouzetech.mouzeschoolapi.api.model.output;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "turmas")
@Getter@Setter
public class ResumoTurmaModel extends RepresentationModel<ResumoTurmaModel> {
	
	@ApiModelProperty(example = "3")
	private Long id;
	
	@ApiModelProperty(example = "Turma 3003")
	private String nome;
}