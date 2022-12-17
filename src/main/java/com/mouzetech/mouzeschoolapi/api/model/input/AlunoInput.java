package com.mouzetech.mouzeschoolapi.api.model.input;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class AlunoInput {
	
	@ApiModelProperty(example = "Nedved Couto", required = true)
	@NotBlank
	private String nome;

	@ApiModelProperty(example = "06/05/1995", required = true, dataType = "date")
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;	
			
	@ApiModelProperty(example = "nedved.couto@gmail.com", required = true)
	@NotBlank
	private String email;
	
	@ApiModelProperty(example = "741812465", required = true)
	@NotBlank
	private String rg;
	
	@ApiModelProperty(example = "92139665040", required = true)
	@NotBlank
	@CPF
	private String cpf;
	
	@ApiModelProperty(example = "2197556992", required = true)
	@NotBlank
	private String telefone;
}