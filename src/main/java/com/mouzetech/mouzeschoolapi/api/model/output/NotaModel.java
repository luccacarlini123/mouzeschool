package com.mouzetech.mouzeschoolapi.api.model.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotaModel {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	private AlunoApenasNomeModel aluno;
	
	private MateriaApenasNomeModel materia;
	
	private TurmaResumoModel turma;
	
	@ApiModelProperty(example = "8.7")
	private Double valor;
	
	@ApiModelProperty(example = "1")
	private Short bimestre;	
}