package com.mouzetech.mouzeschoolapi.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "Pageable")
@Getter
@Setter
public class PageableModelOpenApi {

	@ApiModelProperty(value = "Tamanho do número de elementos na página", example = "3")
	private int size;
	
	@ApiModelProperty(value = "Número da página", example = "1")
	private int page; 
	
	@ApiModelProperty(value = "Propriedade de ordenação, separado por vírgula", example = "nome,desc")
	private List<String> sort;
	
}
