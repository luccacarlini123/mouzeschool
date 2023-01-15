package com.mouzetech.mouzeschoolapi.api.model.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;

@Relation(collectionRelation = "materias")
@Data
public class MateriaModel extends RepresentationModel<MateriaModel> {
	
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
	private String statusMateria;

}