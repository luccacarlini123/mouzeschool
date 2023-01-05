package com.mouzetech.mouzeschoolapi.api.model.input;

import javax.validation.constraints.NotBlank;

import com.mouzetech.mouzeschoolapi.validation.CadastrarTurma;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@CadastrarTurma
public class TurmaInput {
	
	@ApiModelProperty(example = "Turma 1001", required = true)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "ENSINO FUNDAMENTAL", required = true)
	@NotBlank
	private String grauEnsino;
	
	@ApiModelProperty(example = "PRIMEIRO", required = true)
	@NotBlank
	private String serieEnsino;
}
