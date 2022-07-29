package com.mouzetech.mouzeschoolapi.api.model.output;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ResumoMateriaModel {
	private Long id;
	private String nome;
	private String grauEnsino;
	private String serieEnsino;
	private String statusMateria;
}