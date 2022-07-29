package com.mouzetech.mouzeschoolapi.domain.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.mouzetech.mouzeschoolapi.domain.model.enumeration.StatusGeral;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Matricula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate dataCadastro;
	private String codigoMatricula;
	
	@Enumerated(EnumType.STRING)
	private StatusGeral status;
	
}
