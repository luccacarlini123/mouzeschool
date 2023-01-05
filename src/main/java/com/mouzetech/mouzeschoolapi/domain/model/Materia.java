package com.mouzetech.mouzeschoolapi.domain.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.GrauEnsino;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.SerieEnsino;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.StatusGeral;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Materia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	private String nome;
	
	@CreationTimestamp
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCriacao;
	
	private String codigo;
	
	@Enumerated(EnumType.STRING)
	private GrauEnsino grauEnsino;
	
	@Enumerated(EnumType.STRING)
	private SerieEnsino serieEnsino;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status_materia")
	private StatusGeral status;
	
	public boolean desativada() {
		return this.status.equals(StatusGeral.DESATIVADA);
	}
	
	public boolean ativada() {
		return this.status.equals(StatusGeral.ATIVADA);
	}
	
	public void ativar() {
		setStatus(StatusGeral.ATIVADA);
	}
	
	public void desativar() {
		setStatus(StatusGeral.DESATIVADA);
	}
	
	@PrePersist
	private void gerarCodigo() {
		setCodigo(UUID.randomUUID().toString());
	}
}
