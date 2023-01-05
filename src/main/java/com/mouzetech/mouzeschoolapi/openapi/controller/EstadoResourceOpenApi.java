package com.mouzetech.mouzeschoolapi.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.mouzetech.mouzeschoolapi.api.model.input.EstadoInput;
import com.mouzetech.mouzeschoolapi.api.model.output.EstadoModel;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoEstadoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Estados")
public interface EstadoResourceOpenApi {

	@ApiOperation(value = "Busca todos os estados")
	public CollectionModel<ResumoEstadoModel> buscarTodos();
	
	@ApiOperation(value = "Busca um estado por ID")
	public ResponseEntity<EstadoModel> buscarPorId(
			
			@ApiParam(value = "ID do estado", required = true)
			Long estadoId);

	@ApiOperation(value = "Exclui um estado")
	public void excluir(
			@ApiParam(value = "ID do estado", required = true)
			Long estadoId);
	
	@ApiOperation(value = "Salva um estado")
	public ResponseEntity<EstadoModel> salvar(
			@ApiParam(value = "Representa um novo estado a ser cadastrado", name = "corpo")
			EstadoInput estadoInput);
	
	@ApiOperation(value = "Atualiza um estado")
	public void atualizar(
			@ApiParam(value = "Representa um novo estado a ser atualizado", name = "corpo")
			EstadoInput estadoInput, 
			
			@ApiParam(value = "ID do estado", required = true)
			Long estadoId);

	
}
