package com.mouzetech.mouzeschoolapi.api.model.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ResumoMateriaModel {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Matemática")
	private String nome;
	
	@ApiModelProperty(example = "ENSINO_MEDIO")
	private String grauEnsino;
	
	@ApiModelProperty(example = "TERCEIRO_ANO")
	private String serieEnsino;
	
	@ApiModelProperty(example = "ATIVADA")
	private String statusMateria;
}