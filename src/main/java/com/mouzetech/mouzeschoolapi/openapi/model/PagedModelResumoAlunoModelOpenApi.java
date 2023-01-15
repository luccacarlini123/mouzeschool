package com.mouzetech.mouzeschoolapi.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.mouzetech.mouzeschoolapi.api.model.output.ResumoAlunoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel("ResumoAlunosModel")
@Getter
@Setter
public class PagedModelResumoAlunoModelOpenApi {

	private ResumoAlunoModelEmbeddedOpenApi _embedded;
	private Links _links;
	private PageModelOpenApi page;
	
	@Data
	@ApiModel("ResumoAlunoEmbeddedModel")
	public class ResumoAlunoModelEmbeddedOpenApi {
		private List<ResumoAlunoModel> alunos;
	}	
}