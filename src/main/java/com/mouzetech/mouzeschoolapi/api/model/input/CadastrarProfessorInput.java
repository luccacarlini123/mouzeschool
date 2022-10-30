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
public class CadastrarProfessorInput {
	
	@ApiModelProperty(example = "Carola Nascimento", required = true)
	@NotBlank
	private String nome;

	@ApiModelProperty(example = "04/08/1988", required = true, dataType = "date")
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;	
			
	@ApiModelProperty(example = "carola.nascimento@gmail.com", required = true)
	@NotBlank
	private String email;
	
	@ApiModelProperty(example = "212524793", required = true)
	@NotBlank
	private String rg;
	
	@ApiModelProperty(example = "40454338058", required = true)
	@CPF
	@NotBlank
	private String cpf;
	
	@ApiModelProperty(example = "21992686352", required = true)
	@NotBlank
	private String telefone;
}