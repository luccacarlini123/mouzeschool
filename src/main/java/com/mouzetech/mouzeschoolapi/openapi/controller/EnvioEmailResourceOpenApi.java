package com.mouzetech.mouzeschoolapi.openapi.controller;

import com.mouzetech.mouzeschoolapi.api.model.input.EnvioEmailParaTodosInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Email")
public interface EnvioEmailResourceOpenApi {

	@ApiOperation(value = "Envia email para todos os alunos")
	public void enviarEmailParaTodosAlunosComMatriculaAtiva(
			@ApiParam(value = "Representa um email a ser enviado", required = true)
			EnvioEmailParaTodosInput emailInput);
	
}
