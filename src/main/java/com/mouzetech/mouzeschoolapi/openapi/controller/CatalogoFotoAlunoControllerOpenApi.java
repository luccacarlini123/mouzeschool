package com.mouzetech.mouzeschoolapi.openapi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.mouzetech.mouzeschoolapi.api.model.input.FotoPessoaInput;
import com.mouzetech.mouzeschoolapi.api.model.output.FotoPessoaModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Alunos")
public interface CatalogoFotoAlunoControllerOpenApi {

	@ApiOperation(value = "Salva/atualiza a foto de um aluno")
	public FotoPessoaModel atualizarFoto(

			@ApiParam(value = "ID do aluno", required = true)
			Long alunoId, 
			
			@ApiParam(value = "Representação de uma foto a ser salva/atualizada")
			FotoPessoaInput fotoPessoaInput,
			
			@ApiParam(value = "Arquivo da foto do produto (máximo 8000KB, apenas JPG e PNG)", required = true)
			MultipartFile arquivo) throws IOException;
	
	@ApiOperation(value = "Busca foto do aluno")
	public ResponseEntity<?> buscarFotoDoAluno(

			@ApiParam(value = "ID do aluno", required = true)
			Long alunoId,
			
			@ApiParam(value = "Tipos de retorno aceito pelo requisitante. Exemplo: image/jpeg, image/png", example = "image/jpeg", required = false, hidden = true) 
			String acceptHeaders) throws HttpMediaTypeNotAcceptableException;
	
	@ApiOperation(value = "Excluir foto do aluno")
	public void excluirFotoDoAluno(
			@ApiParam(value = "ID do aluno", required = true)
			Long alunoId);
	
}
