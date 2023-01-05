package com.mouzetech.mouzeschoolapi.api.model.output;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(value = "professores")
@Getter@Setter
public class ProfessorModel extends RepresentationModel<ProfessorModel> {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "José")
	private String nome;
	
	@ApiModelProperty(example = "06/06/1986")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	@ApiModelProperty(example = "jose.campos@outlook.com")
	private String email;
	
	@ApiModelProperty(example = "279564429")
	private String rg;
	
	@ApiModelProperty(example = "18935954802")
	private String cpf;
	
	@ApiModelProperty(example = "21978885632")
	private String telefone;
	
	@ApiModelProperty(value = "Representa uma matrícula", name = "corpe")
	private MatriculaModel matricula;
}