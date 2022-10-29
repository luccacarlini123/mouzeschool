package com.mouzetech.mouzeschoolapi.openapi.model;

import java.time.OffsetDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("ProblemaInternalServerErrorOpenApi")
@Getter
@Setter
public class ProblemaInternalServerErrorOpenApi {

	@ApiModelProperty(example = "500", position = 1)
	private Integer status;
	
	@ApiModelProperty(example = "https://mouzefood.com.br/erro-interno-servidor", position = 5)
	private String type;
	
	@ApiModelProperty(example = "Erro interno do servidor", position = 10)
	private String title;
	
	@ApiModelProperty(example = "Trace de alguma exception", position = 15)
	private String detail;
	
	@ApiModelProperty(example = "2022-10-25T21:56:00.7067691Z", position = 25)
	private OffsetDateTime date;
	
}
