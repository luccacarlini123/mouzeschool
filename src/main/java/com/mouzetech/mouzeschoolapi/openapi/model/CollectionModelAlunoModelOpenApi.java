package com.mouzetech.mouzeschoolapi.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.mouzetech.mouzeschoolapi.api.model.output.AlunoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel("AlunosModel")
@Getter
@Setter
public class CollectionModelAlunoModelOpenApi {

	private AlunoModelEmbeddedOpenApi _embedded;
	private Links _links;
	
	@Data
	@ApiModel("AlunoEmbeddedModel")
	public class AlunoModelEmbeddedOpenApi {
		private List<AlunoModel> alunos;
	}	
}