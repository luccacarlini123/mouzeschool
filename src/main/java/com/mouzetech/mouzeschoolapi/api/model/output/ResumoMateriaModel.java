package com.mouzetech.mouzeschoolapi.api.model.output;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.mouzetech.mouzeschoolapi.domain.model.enumeration.GrauEnsino;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.SerieEnsino;

import io.swagger.annotations.ApiModelProperty;

@Relation(collectionRelation = "materias")
public class ResumoMateriaModel extends RepresentationModel<ResumoMateriaModel> {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Matemática")
	private String nome;
	
	@ApiModelProperty(example = "ENSINO MÉDIO")
	private String grauEnsino;
	
	@ApiModelProperty(example = "TERCEIRO ANO")
	private String serieEnsino;
	
	@ApiModelProperty(example = "ATIVADA")
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGrauEnsino() {
		return GrauEnsino.getDescricaoByEnum(GrauEnsino.valueOf(this.grauEnsino));
	}

	public void setGrauEnsino(String grauEnsino) {
		this.grauEnsino = grauEnsino;
	}

	public String getSerieEnsino() {
		return SerieEnsino.getDescricaoByEnum(SerieEnsino.valueOf(this.serieEnsino));
	}

	public void setSerieEnsino(String serieEnsino) {
		this.serieEnsino = serieEnsino;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}