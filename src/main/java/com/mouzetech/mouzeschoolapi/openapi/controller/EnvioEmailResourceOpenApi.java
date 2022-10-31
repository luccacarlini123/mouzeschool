package com.mouzetech.mouzeschoolapi.openapi.controller;

import com.mouzetech.mouzeschoolapi.api.model.input.EnvioEmailParaTodosInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Email")
public interface EnvioEmailResourceOpenApi {

	@ApiOperation(value = "Envia email para todos os alunos")
	void enviarEmailParaTodosAlunosComMatriculaAtiva(
			@ApiParam(value = "Representa um email a ser enviado", required = true)
			EnvioEmailParaTodosInput emailInput);
	
	@ApiOperation(value = "Envia email para todos os professores")
	void enviarEmailParaTodosProfessoresComMatriculaAtiva(
			@ApiParam(value = "Representa um email a ser enviado", required = true)
			EnvioEmailParaTodosInput emailInput);
	
	@ApiOperation(value = "Envia email para um aluno por ID")
	void enviarEmailParaAluno(
			@ApiParam(value = "Representa um email a ser enviado", required = true)
			EnvioEmailParaTodosInput emailInput, 
			
			@ApiParam(value = "ID do aluno", required = true) 
			Long alunoId);
	
	@ApiOperation(value = "Envia email para um professor por ID")
	void enviarEmailParaProfessor(
			@ApiParam(value = "Representa um email a ser enviado", required = true)
			EnvioEmailParaTodosInput emailInput, 
			
			@ApiParam(value = "ID do professor", required = true) 
			Long professorId);
	
}
