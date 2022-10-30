package com.mouzetech.mouzeschoolapi.api.model.output;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class MateriaModel {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Geografia")
	private String nome;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(example = "29/10/2022", dataType = "date")
	private LocalDate dataCriacao;
	
	@ApiModelProperty(example = "GEO202201")
	private String codigo;
	
	@ApiModelProperty(example = "ENSINO_MEDIO")
	private String grauEnsino;
	
	@ApiModelProperty(example = "TERCEIRO_ANO")
	private String serieEnsino;
	
	@ApiModelProperty(example = "ATIVADA")
	private String statusMateria;
}