package com.mouzetech.mouzeschoolapi.domain.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Nota {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "aluno_id")
	private Aluno aluno;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "materia_id")
	private Materia materia;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "turma_id")
	private Turma turma;
	
	private Double valor;
	
	private Short bimestre;
	
	@CreationTimestamp
	private LocalDate dataCadastro;
	
	public String getNomeMateria() {
		return this.getMateria().getNome();
	}
}