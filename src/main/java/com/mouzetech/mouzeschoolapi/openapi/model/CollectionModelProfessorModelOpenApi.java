package com.mouzetech.mouzeschoolapi.openapi.model;

import com.mouzetech.mouzeschoolapi.api.model.output.ProfessorModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("ProfessoresModel")
@Getter
@Setter
public class CollectionModelProfessorModelOpenApi {

	private ProfessorModelEmbeddedOpenApi _embedded;
	private Links _links;

	@Data
	@ApiModel("ProfessorEmbeddedModel")
	public class ProfessorModelEmbeddedOpenApi {
		private List<ProfessorModel> professores;
	}
}