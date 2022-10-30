package com.mouzetech.mouzeschoolapi.openapi.controller;

import org.springframework.http.ResponseEntity;

import com.mouzetech.mouzeschoolapi.api.model.filter.NotaFilter;
import com.mouzetech.mouzeschoolapi.api.model.input.CadastrarNotaInput;
import com.mouzetech.mouzeschoolapi.api.model.output.NotasDaTurmaModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Notas")
public interface NotaResourceOpenApi {

	@ApiOperation(value = "Busca todas as notas")
	public ResponseEntity<?> buscarNotas();
	
	@ApiOperation(value = "Cadastra uma nota")
	public void cadastrarNota(
			@ApiParam(value = "Representação e uma nota a ser cadastrada", name = "corpo")
			CadastrarNotaInput dto);
	
	@ApiOperation(value = "Busca as notas por turma filtrando os dados")
	public NotasDaTurmaModel buscarNotasPorTurma(
			@ApiParam(value = "ID da turma", required = true)
			Long turmaId, 
			
			@ApiParam(value = "Representação de um filtro de nota")
			NotaFilter notaFilter);
	
}
