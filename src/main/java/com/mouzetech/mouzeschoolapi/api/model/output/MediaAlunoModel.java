package com.mouzetech.mouzeschoolapi.api.model.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MediaAlunoModel {
	
	@ApiModelProperty(example = "Matemática")
	private String materia;
	
	@ApiModelProperty(example = "9.6")
	private Double media;
}