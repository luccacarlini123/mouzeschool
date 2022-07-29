package com.mouzetech.mouzeschoolapi.domain.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "turma_materia_professor")
@EqualsAndHashCode
@Data
public class TurmaMateriaProfessor {

	@EmbeddedId
	private TurmaMateriaProfessorId turmaMateriaProfessorId;
	
	@ManyToOne
	@JoinColumn(name = "professor_id", nullable = true)
	private Professor professor;
	
}
