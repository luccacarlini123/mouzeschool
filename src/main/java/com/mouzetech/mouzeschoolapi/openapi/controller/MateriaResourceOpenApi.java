package com.mouzetech.mouzeschoolapi.openapi.controller;

import java.util.List;

import com.mouzetech.mouzeschoolapi.api.model.input.CadastrarMateriaInput;
import com.mouzetech.mouzeschoolapi.api.model.output.MateriaModel;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoMateriaModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Matérias")
public interface MateriaResourceOpenApi {

	@ApiOperation(value = "Busca todas as matérias")
	public List<ResumoMateriaModel> buscarTodas();
	
	@ApiOperation(value = "Busca matéria por ID")
	public MateriaModel buscarPorId(
			@ApiParam(value = "ID da turma", required = true)
			Long materiaId);
	
	@ApiOperation(value = "Busca matéria por nome")
	public List<MateriaModel> buscarPorNomeContaining(
			@ApiParam(value = "Nome da turma", required = true)
			String nome);
	
	@ApiOperation(value = "Salva uma nova matéria")
	public MateriaModel cadastrar(
			@ApiParam(value = "Representação de uma matéria a ser cadastrada", name = "corpo")
			CadastrarMateriaInput dto);
	
	@ApiOperation(value = "Ativa turma")
	public void ativarTurma(
			@ApiParam(value = "ID da turma", required = true)
			Long materiaId);
	
	@ApiOperation(value = "Desativa turma")
	public void desativarTurma(
			@ApiParam(value = "ID da turma", required = true)
			Long materiaId);
	
	@ApiOperation(value = "Exclui turma")
	public void excluir(
			@ApiParam(value = "ID da turma", required = true)
			Long materiaId);
}