package com.mouzetech.mouzeschoolapi.api.model.output;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "alunos")
@Getter@Setter
public class ResumoAlunoModel extends RepresentationModel<ResumoAlunoModel> {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Lucca Carlini")
	private String nome;	
	
	@ApiModelProperty(example = "lucca.carlini18@gmail.com")
	private String email;
}