package com.mouzetech.mouzeschoolapi.api.model.output;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.StatusGeral;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatriculaModel {
	
	@ApiModelProperty(example = "1")
	private Long id;	
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(example = "06/10/2022")
	private LocalDate dataCadastro;
	
	@ApiModelProperty(example = "202201334494")
	private String codigoMatricula;
	
	@ApiModelProperty(example = "ATIVADA")
	private StatusGeral status;
}