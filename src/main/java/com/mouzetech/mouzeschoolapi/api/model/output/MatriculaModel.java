package com.mouzetech.mouzeschoolapi.api.model.output;

import java.time.LocalDate;

import com.mouzetech.mouzeschoolapi.domain.model.enumeration.StatusGeral;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatriculaModel {
	private Long id;	
	private LocalDate dataCadastro;
	private String codigoMatricula;
	private StatusGeral status;
}