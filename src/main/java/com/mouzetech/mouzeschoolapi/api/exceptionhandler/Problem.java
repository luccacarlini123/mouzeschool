package com.mouzetech.mouzeschoolapi.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel(value = "Problema")
@JsonInclude(Include.NON_NULL)
@Builder
@Getter
public class Problem {

	
	@ApiModelProperty(example = "400")
	private Integer status;
	
	@ApiModelProperty(example = "https://mouzeschool.com.br/dados-invalido")
	private String type;
	
	@ApiModelProperty(example = "Dados inválidos")
	private String title;
	
	@ApiModelProperty(example = "Há campos que estão inválidos, corrija-os e tente novamente.")
	private String detail;
	
	private String userMessage;
	
	@ApiModelProperty(example = "2022-10-29T20:00:24.0162516-03:00")
	private OffsetDateTime date;
	
	@ApiModelProperty(value = "Objetos do problema")
	private List<Object> problemObjects;
	
	@ApiModel(value = "Objeto do problema")
	@Getter
	@Builder
	public static class Object {
		
		@ApiModelProperty(example = "nome")
		private String objectName;
		
		@ApiModelProperty(example = "O nome do professor é obrigatório")
		private String userMessage;
	}
}