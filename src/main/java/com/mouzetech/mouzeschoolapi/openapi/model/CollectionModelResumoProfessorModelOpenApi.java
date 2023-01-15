package com.mouzetech.mouzeschoolapi.openapi.model;

import com.mouzetech.mouzeschoolapi.api.model.output.ResumoProfessorModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("ResumoProfessoresModel")
@Getter
@Setter
public class CollectionModelResumoProfessorModelOpenApi {

	private ResumoProfessorModelEmbeddedOpenApi _embedded;
	private Links _links;

	@Data
	@ApiModel("ResumoProfessorEmbeddedModel")
	public class ResumoProfessorModelEmbeddedOpenApi {
		private List<ResumoProfessorModel> professores;
	}
}