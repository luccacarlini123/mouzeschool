package com.mouzetech.mouzeschoolapi.api.model.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ResumoProfessorModel {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Lucca")
	private String nome;	
	
	@ApiModelProperty(example = "lucca.lucca@gmail.com")
	private String email;
	
}
