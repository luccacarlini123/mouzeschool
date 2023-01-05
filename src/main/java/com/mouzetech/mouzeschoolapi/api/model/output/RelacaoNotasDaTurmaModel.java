package com.mouzetech.mouzeschoolapi.api.model.output;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class RelacaoNotasDaTurmaModel extends RepresentationModel<RelacaoNotasDaTurmaModel> {
	@ApiModelProperty(example = "Lucca")
	private String nomeAluno;
	
	@ApiModelProperty(example = "1")
	private Short bimestre;
	
	@ApiModelProperty(example = "Matemática")
	private String nomeMateria;
	
	@ApiModelProperty(example = "9.6")
	private Double valor;	
}