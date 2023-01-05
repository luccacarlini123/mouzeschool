package com.mouzetech.mouzeschoolapi.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.mouzetech.mouzeschoolapi.api.model.input.MateriaInput;
import com.mouzetech.mouzeschoolapi.api.model.output.MateriaModel;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoMateriaModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Matérias")
public interface MateriaResourceOpenApi {

	@ApiOperation(value = "Busca todas as matérias")
	public CollectionModel<ResumoMateriaModel> buscarTodas();
	
	@ApiOperation(value = "Busca matéria por ID")
	public MateriaModel buscarPorId(
			@ApiParam(value = "ID da matéria", required = true)
			Long materiaId);
	
	@ApiOperation(value = "Busca matéria por nome")
	public CollectionModel<MateriaModel> buscarPorNomeContaining(
			@ApiParam(value = "Nome da matéria", required = true)
			String nome);
	
	@ApiOperation(value = "Salva uma nova matéria")
	public MateriaModel cadastrar(
			@ApiParam(value = "Representação de uma matéria a ser cadastrada", name = "corpo")
			MateriaInput dto);
	
	@ApiOperation(value = "Ativa matéria")
	public ResponseEntity<Void> ativarMateria(
			@ApiParam(value = "ID da matéria", required = true)
			Long materiaId);
	
	@ApiOperation(value = "Desativa matéria")
	public ResponseEntity<Void> desativarMateria(
			@ApiParam(value = "ID da matéria", required = true)
			Long materiaId);
	
	@ApiOperation(value = "Exclui matéria")
	public void excluir(
			@ApiParam(value = "ID da matéria", required = true)
			Long materiaId);
}