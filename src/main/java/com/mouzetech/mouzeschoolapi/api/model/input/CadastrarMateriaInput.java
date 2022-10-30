package com.mouzetech.mouzeschoolapi.api.model.input;

import javax.validation.constraints.NotBlank;

import com.mouzetech.mouzeschoolapi.validation.CadastrarMateria;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@CadastrarMateria
public class CadastrarMateriaInput {

	@ApiModelProperty(example = "Matemática", required = true)
	@NotBlank
	private String nome;	
	
	@ApiModelProperty(example = "ENSINO MÉDIO", required = true)
	@NotBlank
	private String grauEnsino;
	
	@ApiModelProperty(example = "TERCEIRO", required = true)
	@NotBlank
	private String serieEnsino;
	
}
