package com.mouzetech.mouzeschoolapi.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.mouzetech.mouzeschoolapi.api.model.input.CidadeInput;
import com.mouzetech.mouzeschoolapi.api.model.output.CidadeModel;
import com.mouzetech.mouzeschoolapi.api.model.output.CidadeResumoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Cidades")
public interface CidadeResourceResourceOpenApi {

	@ApiOperation(value = "Buscar todas")
	public CollectionModel<CidadeResumoModel> buscarTodos();
	
	@ApiOperation(value = "Buscar por ID")
	public ResponseEntity<CidadeModel> buscarPorId(
			@ApiParam(value = "ID da cidade", required = true)
			Long cidadeId);
	
	@ApiOperation(value = "Excluir por ID")
	public void excluirPorId(
			@ApiParam(value = "ID da cidade", required = true)
			Long cidadeId);
	
	@ApiOperation(value = "Atualizar cidade")
	public void atualizar(
			@ApiParam(value = "Representação de uma nova cidade a ser atualizada", name = "corpo") 
			CidadeInput input, 
			
			@ApiParam(value = "ID da cidade", required = true)
			Long cidadeId);
	
	@ApiOperation(value = "Cadastrar uma nova cidade")
	public ResponseEntity<CidadeModel> salvar(
			@ApiParam(value = "Representação de uma nova cidade a ser cadastrada", name = "corpo") 
			CidadeInput input);
}