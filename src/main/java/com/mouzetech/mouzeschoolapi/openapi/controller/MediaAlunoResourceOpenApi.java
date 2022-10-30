package com.mouzetech.mouzeschoolapi.openapi.controller;

import org.springframework.http.ResponseEntity;

import com.mouzetech.mouzeschoolapi.api.model.output.MediaAlunoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Médias")
public interface MediaAlunoResourceOpenApi {

	@ApiOperation(value = "Busca as médias das matérias de um aluno em uma turma", 
			notes = "Só será calculado a média das matérias que possuem notas lançadas nos 4 bimestres")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Média buscada com sucesso", response = MediaAlunoModel.class),
		@ApiResponse(code = 204, message = "Sucesso, porém o aluno não possui nenhuma média finalizada ainda.")
	})
	public ResponseEntity<?> buscarMediaAluno(
			@ApiParam(value = "ID do aluno")
			Long alunoId, 
			
			@ApiParam(value = "ID da turma")
			Long turmaId);
	
}
