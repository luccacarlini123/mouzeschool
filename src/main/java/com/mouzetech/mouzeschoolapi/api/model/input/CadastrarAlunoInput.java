package com.mouzetech.mouzeschoolapi.api.model.input;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CadastrarAlunoInput {
	
	@NotBlank
	private String nome;

	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;	
			
	@NotBlank
	private String email;
	
	@NotBlank
	private String rg;
	
	@NotBlank
	@CPF
	private String cpf;
	
	@NotBlank
	private String telefone;
}