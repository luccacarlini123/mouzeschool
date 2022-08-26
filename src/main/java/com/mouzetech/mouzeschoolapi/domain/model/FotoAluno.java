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
@Table(name = "foto_aluno")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class FotoAluno {
	
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "aluno_id")
	private Long id;
	
	@OneToOne
	@MapsId
	private Aluno aluno;
	
	private String descricao;
	private String nomeArquivo;
	private String contentType;
	private Long tamanho;
	
	public Long getAlunoId() {
		if(getAluno() != null) {
			return getAluno().getId();
		}
		return null;
	}
	
}