package com.mouzetech.mouzeschoolapi.openapi.controller;

import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Relatórios")
public interface RelatoriosResourceOpenApi {

	@ApiOperation(value = "Relatório dos alunos ativos de uma turma")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Relatório emitido com sucesso")
	})
	ResponseEntity<byte[]> relatorioAlunosDaTurma(
			@ApiParam(value = "ID da turma", required = true)
			Long turmaId);	
}