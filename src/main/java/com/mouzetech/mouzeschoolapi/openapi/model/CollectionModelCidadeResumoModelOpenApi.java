package com.mouzetech.mouzeschoolapi.openapi.model;

import com.mouzetech.mouzeschoolapi.api.model.output.CidadeResumoModel;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("CidadesResumoModel")
@Getter
@Setter
public class CollectionModelCidadeResumoModelOpenApi {

	private CidadeResumoModelEmbedded _embedded;
	private Links link;
	
	@ApiModel("CidadeResumoModelEmbedded")
	@Getter
	@Setter
	private final class CidadeResumoModelEmbedded{
		private List<CidadeResumoModel> cidades;
	}
	
}
