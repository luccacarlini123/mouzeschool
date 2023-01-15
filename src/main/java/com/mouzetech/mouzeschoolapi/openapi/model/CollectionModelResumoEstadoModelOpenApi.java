package com.mouzetech.mouzeschoolapi.openapi.model;

import com.mouzetech.mouzeschoolapi.api.model.output.ResumoTurmaModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("ResumoEstadosModel")
@Getter
@Setter
public class CollectionModelResumoEstadoModelOpenApi {

	private ResumoEstadoModelEmbeddedOpenApi _embedded;
	private Links _links;

	@Data
	@ApiModel("ResumoEstadoEmbeddedModel")
	public class ResumoEstadoModelEmbeddedOpenApi {
		private List<ResumoTurmaModel> professores;
	}
}