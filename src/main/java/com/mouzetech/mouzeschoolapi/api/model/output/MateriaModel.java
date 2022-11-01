package com.mouzetech.mouzeschoolapi.api.model.output;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.GrauEnsino;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.SerieEnsino;

import io.swagger.annotations.ApiModelProperty;

public class MateriaModel {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Geografia")
	private String nome;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(example = "29/10/2022", dataType = "date")
	private LocalDate dataCriacao;
	
	@ApiModelProperty(example = "GEO202201")
	private String codigo;
	
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

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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