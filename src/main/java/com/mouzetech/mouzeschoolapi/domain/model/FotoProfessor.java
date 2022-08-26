package com.mouzetech.mouzeschoolapi.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "foto_professor")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class FotoProfessor {

	@Id
	@EqualsAndHashCode.Include
	@Column(name = "professor_id")
	private Long id;
	
	@OneToOne
	@MapsId
	private Professor professor;
	
	private String descricao;
	private String nomeArquivo;
	private String contentType;
	private Long tamanho;
	
	public Long getAlunoId() {
		if(getProfessor() != null) {
			return getProfessor().getId();
		}
		return null;
	}
	
}