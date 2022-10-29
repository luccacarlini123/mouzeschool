package com.mouzetech.mouzeschoolapi.api.model.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ResumoAlunoModel {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Lucca Carlini")
	private String nome;	
	
	@ApiModelProperty(example = "lucca.carlini18@gmail.com")
	private String email;
}