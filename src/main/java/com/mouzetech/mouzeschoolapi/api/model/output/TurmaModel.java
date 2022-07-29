package com.mouzetech.mouzeschoolapi.api.model.output;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class TurmaModel {
	private Long id;
	private String nome;
	private LocalDate dataCriacao;
	private String statusTurma;
	private String grauEnsino;
	private String serieEnsino;
}