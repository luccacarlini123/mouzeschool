package com.mouzetech.mouzeschoolapi.openapi.model;

import com.mouzetech.mouzeschoolapi.api.model.output.ResumoTurmaModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("ResumoTurmasModel")
@Getter
@Setter
public class CollectionModelResumoTurmaModelOpenApi {

	private ResumoTurmaModelEmbeddedOpenApi _embedded;
	private Links _links;

	@Data
	@ApiModel("ResumoTurmaEmbeddedModel")
	public class ResumoTurmaModelEmbeddedOpenApi {
		private List<ResumoTurmaModel> professores;
	}
}