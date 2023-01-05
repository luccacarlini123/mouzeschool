package com.mouzetech.mouzeschoolapi.api.model.output;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class TurmaModel extends RepresentationModel<TurmaModel> {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Turma 3001")
	private String nome;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(example = "29/10/2022")
	private LocalDate dataCriacao;
	
	@ApiModelProperty(example = "ATIVADA")
	private String statusTurma;
	
	@ApiModelProperty(example = "ENSINO_MEDIO")
	private String grauEnsino;
	
	@ApiModelProperty(example = "TERCEIRO_ANO")
	private String serieEnsino;
}