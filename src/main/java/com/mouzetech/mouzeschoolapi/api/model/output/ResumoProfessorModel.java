package com.mouzetech.mouzeschoolapi.api.model.output;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "professores")
@Getter@Setter
public class ResumoProfessorModel extends RepresentationModel<ResumoProfessorModel> {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Lucca")
	private String nome;	
	
	@ApiModelProperty(example = "lucca.lucca@gmail.com")
	private String email;
	
}
