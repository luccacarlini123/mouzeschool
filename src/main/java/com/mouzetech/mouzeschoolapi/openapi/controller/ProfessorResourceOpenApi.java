package com.mouzetech.mouzeschoolapi.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.mouzetech.mouzeschoolapi.api.model.input.EnderecoInput;
import com.mouzetech.mouzeschoolapi.api.model.input.ProfessorInput;
import com.mouzetech.mouzeschoolapi.api.model.output.EnderecoModel;
import com.mouzetech.mouzeschoolapi.api.model.output.ProfessorModel;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoProfessorModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Professores")
public interface ProfessorResourceOpenApi {

	@ApiOperation(value = "Busca todos os professores")
	public CollectionModel<ResumoProfessorModel> buscarProfessores();
	
	@ApiOperation(value = "Busca professor por ID")
	public ResponseEntity<ProfessorModel> buscarPorId(
			@ApiParam(value = "ID do professor", required = true)
			Long professorId);
	
	@ApiOperation(value = "Busca professores por email")
	public CollectionModel<ProfessorModel> buscarPorEmail(
			@ApiParam(value = "Pode ser um pedaço do email ou o email completo", required = true) String email);
	
	@ApiOperation(value = "Busca professores por nome")
	public CollectionModel<ProfessorModel> buscarPorNome(
			@ApiParam(value = "Pode ser um pedaço do nome ou o nome completo", required = true)
			String nome);
	
	@ApiOperation(value = "Salva um professor")
	public ProfessorModel cadastrar(
			@ApiParam(value = "Representação de um novo professor a ser salvo", name = "corpo")
			ProfessorInput dto);
	
	@ApiOperation(value = "Cadastra endereço para professor")
	public void cadastrarEndereco(
			@ApiParam(value = "Representação de um endereço a ser salvo")
			EnderecoInput enderecoInput, 
			
			@ApiParam(value = "ID do aluno", required = true)
			Long professorId);
	
	@ApiOperation(value = "Busca endereço")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Endereço encontrado com sucesso", response = EnderecoModel.class),
		@ApiResponse(code = 204, message = "Professor encontrado, porém não contém endereço")
	})
	public ResponseEntity<?> buscarEndereco(
			@ApiParam(value = "ID do professor", required = true)
			Long alunoId);
	
	@ApiOperation(value = "Atualiza um professor")
	public void atualizar(
			@ApiParam(value = "Representação de um professor a ser atualizado", name = "corpo")
			ProfessorInput dto, 
			
			@ApiParam(value = "ID do professor", required = true)
			Long professorId);
	
	@ApiOperation(value = "Exclui professor")
	public void excluir(
			@ApiParam(value = "ID do professor", required = true)
			Long professorId);
	
	@ApiOperation(value = "Ativa matrícula")
	public ResponseEntity<Void> ativarMatricula(
			@ApiParam(value = "ID do professor", required = true)
			Long professorId);
	
	@ApiOperation(value = "Desativa matrícula")
	public ResponseEntity<Void> desativarMatricula(
			@ApiParam(value = "ID do professor", required = true)
			Long professorId);	
}