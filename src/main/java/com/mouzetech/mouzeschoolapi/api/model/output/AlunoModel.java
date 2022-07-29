package com.mouzetech.mouzeschoolapi.api.model.output;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class AlunoModel {
	private Long id;
	private String nome;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	private String email;
	private String rg;
	private String cpf;
	private String telefone;
	private MatriculaModel matricula;
}