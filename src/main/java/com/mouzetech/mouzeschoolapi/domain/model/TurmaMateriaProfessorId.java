package com.mouzetech.mouzeschoolapi.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Embeddable
@Data
public class TurmaMateriaProfessorId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "turma_id")
	private Turma turma;
	
	@ManyToOne
	@JoinColumn(name = "materia_id")
	private Materia materia;
}
