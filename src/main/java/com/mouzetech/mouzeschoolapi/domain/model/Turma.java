package com.mouzetech.mouzeschoolapi.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.GrauEnsino;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.SerieEnsino;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.StatusGeral;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Turma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	private String nome;
	
	@CreationTimestamp
	@Column(nullable = false)
	private LocalDate dataCriacao;
	
	@Enumerated(EnumType.STRING)
	private StatusGeral statusTurma;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "alunos_turma", 
				joinColumns = @JoinColumn(name = "turma_id"), 
				inverseJoinColumns = @JoinColumn(name = "aluno_id"))
	private List<Aluno> alunos = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private GrauEnsino grauEnsino;
	
	@Enumerated(EnumType.STRING)
	private SerieEnsino serieEnsino;
	
	public boolean desativada() {
		return this.getStatusTurma().equals(StatusGeral.DESATIVADA);
	}
	
	public boolean ativada() {
		return this.getStatusTurma().equals(StatusGeral.ATIVADA);
	}
	
	public void ativar() {
		setStatusTurma(StatusGeral.ATIVADA);
	}
	
	public void desativar() {
		setStatusTurma(StatusGeral.DESATIVADA);
	}
	
	public boolean alunoJaMatriculado(Aluno aluno) {
		return this.getAlunos() != null 
				&& !this.getAlunos().isEmpty() 
				&& this.getAlunos().stream()
					.anyMatch(alunoItem -> alunoItem.equals(aluno));
	}

}
