package com.mouzetech.mouzeschoolapi.api.model.output;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class AlunoModel {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Lucca Barbosa")
	private String nome;
	
	@ApiModelProperty(example = "06/04/1998")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	@ApiModelProperty(example = "lucca.carlini@oulook.com")
	private String email;
	
	@ApiModelProperty(example = "741812460")
	private String rg;
	
	@ApiModelProperty(example = "1697687763")
	private String cpf;
	
	@ApiModelProperty(example = "978885665")
	private String telefone;
	
	@ApiModelProperty(value = "Representação de uma matrícula")
	private MatriculaModel matricula;
}