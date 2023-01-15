package com.mouzetech.mouzeschoolapi.openapi.model;


import com.mouzetech.mouzeschoolapi.api.model.output.ResumoMateriaModel;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;


@ApiModel("MateriasResumoModel")
@Getter
@Setter
public class CollectionModelResumoMateriaModelOpenApi {

	private ResumoMateriaModelEmbedded _embedded;
	private Links link;
	
	@ApiModel("CidadeResumoModelEmbedded")
	@Getter
	@Setter
	private final class ResumoMateriaModelEmbedded{
		private List<ResumoMateriaModel> materias;
	}
	
}