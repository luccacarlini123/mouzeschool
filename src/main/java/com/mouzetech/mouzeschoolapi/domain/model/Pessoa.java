package com.mouzetech.mouzeschoolapi.domain.model;

import java.time.LocalDate;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class Pessoa extends EntidadeBase {
	
	private String nome;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	private String email;
	private String rg;
	private String cpf;
	private String telefone;
}
