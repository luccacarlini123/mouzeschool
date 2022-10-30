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

@Api(tags = "Professores")
public interface CatalogoFotoProfessorControllerOpenApi {

	@ApiOperation(value = "Salva/atualiza a foto de um professor")
	public FotoPessoaModel atualizarFoto(
			@ApiParam(value = "ID do professor", required = true)
			Long professorId, 
			
			@ApiParam(value = "Representação de uma foto a ser salva/atualizada")
			FotoPessoaInput fotoPessoaInput, 
			
			@ApiParam(value = "Arquivo da foto do produto (máximo 8000KB, apenas JPG e PNG)", required = true)
			MultipartFile arquivo) throws IOException;
	
	
	@ApiOperation(value = "Busca foto do professor")
	public ResponseEntity<?> buscarFotoDoProfessor(
			@ApiParam(value = "ID do professor", required = true)
			Long professorId,
			
			@ApiParam(value = "Tipos de retorno aceito pelo requisitante. Exemplo: image/jpeg, image/png", example = "image/jpeg", required = false, hidden = true)
			String acceptHeaders) throws HttpMediaTypeNotAcceptableException;
	
	@ApiOperation(value = "Exclui foto do professor")
	public void excluirFotoDoProfessor(
			@ApiParam(value = "ID do professor", required = true)
			Long professorId);

	
}
