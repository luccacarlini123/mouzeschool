package com.mouzetech.mouzeschoolapi.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import com.mouzetech.mouzeschoolapi.api.model.input.AlunoInput;
import com.mouzetech.mouzeschoolapi.api.model.input.EnderecoInput;
import com.mouzetech.mouzeschoolapi.api.model.output.AlunoModel;
import com.mouzetech.mouzeschoolapi.api.model.output.EnderecoModel;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoAlunoModel;
import com.mouzetech.mouzeschoolapi.openapi.model.ProblemaNotFoundOpenApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Alunos")
public interface AlunoResourceOpenApi {

	@ApiOperation(value = "Busca paginado todos os alunos", notes = "Por padrão trará apenas 10 registros, se possuir.")
	public PagedModel<ResumoAlunoModel> buscarAlunos(Pageable pageable);

	@ApiOperation(value = "Busca aluno por ID")
	public ResponseEntity<AlunoModel> buscarPorId(
			@ApiParam(value = "ID do aluno", required = true) 
			Long alunoId);
	
	@ApiOperation(value = "Busca alunos por email")
	public CollectionModel<AlunoModel> buscarPorEmail(
			@ApiParam(value = "Email do aluno", required = true)  
			String email);
	
	@ApiOperation(value = "Busca alunos por nome")
	public CollectionModel<AlunoModel> buscarPorNome(
			@ApiParam(value = "Nome do aluno", required = true) 
			String nome);
	
	@ApiOperation(value = "Salva um aluno")
	public ResponseEntity<AlunoModel> cadastrar(
			@ApiParam(value = "Representação de um aluno a ser salvo", name = "corpo")
			AlunoInput dto);
	
	@ApiOperation(value = "Cadastra endereço para aluno")
	public void cadastrarEndereco(
			@ApiParam(value = "Representação de um endereço a ser salvo") 
			EnderecoInput enderecoInput, 

			@ApiParam(value = "ID do aluno", required = true)
			Long alunoId);
	
	@ApiOperation(value = "Atualiza um aluno")
	public void atualizar(
			@ApiParam(value = "Representação de um aluno a ser atualizado", name = "corpo")
			AlunoInput dto, 
			
			@ApiParam(value = "ID do aluno", required = true)
			Long alunoId);
	
	@ApiOperation(value = "Ativa matrícula")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Recurso não encontrado", response = ProblemaNotFoundOpenApi.class)
	})
	public ResponseEntity<Void> ativarMatricula(
			@ApiParam(value = "ID do aluno", required = true) 
			Long alunoId);
	
	@ApiOperation(value = "Desativa matrícula")
	public ResponseEntity<Void> desativarMatricula(
			@ApiParam(value = "ID do aluno", required = true)
			Long alunoId);
	
	@ApiOperation(value = "Exclui aluno")
	public void excluir(
			@ApiParam(value = "ID do aluno", required = true) 
			Long alunoId);
	
	@ApiOperation(value = "Busca endereço")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Endereço encontrado com sucesso", response = EnderecoModel.class),
		@ApiResponse(code = 204, message = "Aluno encontrado, porém não contém endereço")
	})
	public ResponseEntity<?> buscarEndereco(
			@ApiParam(value = "ID do aluno", required = true)
			Long alunoId);
}