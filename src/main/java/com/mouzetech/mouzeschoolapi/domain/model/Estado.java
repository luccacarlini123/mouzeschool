package com.mouzetech.mouzeschoolapi.domain.model;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Data
public class Estado extends EntidadeBase {
	private String nome;
	private String sigla;
}
