package com.mouzetech.mouzeschoolapi.openapi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.mouzetech.mouzeschoolapi.api.model.input.CadastrarAlunoInput;
import com.mouzetech.mouzeschoolapi.api.model.input.EnderecoInput;
import com.mouzetech.mouzeschoolapi.api.model.output.AlunoModel;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoAlunoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Alunos")
public interface AlunoResourceOpenApi {

	@ApiOperation(value = "Busca todos os alunos")
	public Page<ResumoAlunoModel> buscarAlunos(Pageable pageable);

	@ApiOperation(value = "Buscar aluno por ID")
	public ResponseEntity<AlunoModel> buscarPorId(
			@ApiParam(value = "ID do aluno", required = true) 
			Long alunoId);
	
	@ApiOperation(value = "Buscar aluno por email")
	public ResponseEntity<List<AlunoModel>> buscarPorEmail(
			@ApiParam(value = "Email do aluno", required = true)  
			String email);
	
	@ApiOperation(value = "Buscar aluno por nome")
	public ResponseEntity<List<AlunoModel>> buscarPorNome(
			@ApiParam(value = "Nome do aluno", required = true) 
			String nome);
	
	@ApiOperation(value = "Salvar aluno")
	public ResponseEntity<AlunoModel> cadastrar(
			@ApiParam(value = "Representação de um aluno a ser salvo", name = "corpo")
			CadastrarAlunoInput dto);
	
	@ApiOperation(value = "Cadastrar endereço para aluno")
	public void cadastrarEndereco(
			@ApiParam(value = "Representação de um endereço a ser salvo") 
			EnderecoInput enderecoInput, 

			@ApiParam(value = "ID do aluno", required = true)
			Long alunoId);
	
	@ApiOperation(value = "Atualizar aluno")
	public void atualizar(
			@ApiParam(value = "Representação de um aluno a ser atualizado", name = "corpo")
			CadastrarAlunoInput dto, 
			
			@ApiParam(value = "ID do aluno", required = true)
			Long alunoId);
	
	@ApiOperation(value = "Ativar matrícula")
	public void ativarMatricula(
			@ApiParam(value = "ID do aluno", required = true) 
			Long alunoId);
	
	@ApiOperation(value = "Desativar matrícula")
	public void desativarMatricula(
			@ApiParam(value = "ID do aluno", required = true)
			Long alunoId);
	
	@ApiOperation(value = "Excluir matrícula")
	public void excluir(
			@ApiParam(value = "ID do aluno", required = true) 
			Long alunoId);
	
}
