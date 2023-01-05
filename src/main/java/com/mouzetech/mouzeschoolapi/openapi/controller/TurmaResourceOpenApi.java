package com.mouzetech.mouzeschoolapi.openapi.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.mouzetech.mouzeschoolapi.api.model.input.AlocarProfessorEmTurmaInput;
import com.mouzetech.mouzeschoolapi.api.model.input.TurmaInput;
import com.mouzetech.mouzeschoolapi.api.model.output.GradeCurricularModel;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoAlunoModel;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoTurmaModel;
import com.mouzetech.mouzeschoolapi.api.model.output.TurmaModel;
import com.mouzetech.mouzeschoolapi.domain.model.Turma;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Turmas")
public interface TurmaResourceOpenApi {

	@ApiOperation(value = "Busca todas as turmas")
	public CollectionModel<ResumoTurmaModel> buscarTurmas(
			@ApiParam(value = "Parâmetro que define se irá buscar as turmas ativas ou desativadas. Por padrão é TRUE", required = false, type = "query")
			boolean ativo);
	
	@ApiOperation(value = "Busca turma por ID")
	public TurmaModel buscarPorId(
			@ApiParam(value = "ID da turma", required = true)
			Long turmaId);
	
	@ApiOperation(value = "Busca grade curricular da turma")
	public GradeCurricularModel buscarGradeCurricularTurma(
			@ApiParam(value = "ID da turma", required = true)
			Long turmaId);
	
	@ApiOperation(value = "Busca alunos da turma")
	public List<ResumoAlunoModel> buscarAlunosDaTurma(
			@ApiParam(value = "ID da turma", required = true)
			Long turmaId);
	
	@ApiOperation(value = "Salva uma nova turma")
	public ResponseEntity<?> cadastrar(
			@ApiParam(value = "Representação do cadastro de uma nova turma", name = "corpo", required = true)
			TurmaInput dto);
	
	@ApiOperation(value = "Aloca professor em turma")
	public void alocarProfessorEmTurma(
			@ApiParam(value = "Representação de um objeto que aloca um professor em uma turma", name = "corpo", required = true)
			AlocarProfessorEmTurmaInput dto);
	
	@ApiOperation(value = "Matricula aluno em turma")
	public void matricularAlunoEmTurma(
			@ApiParam(value = "ID do aluno", required = true, type = "query")
			Long alunoId, 

			@ApiParam(value = "ID da turma", required = true, type = "query")
			Long turmaId);
	
	@ApiOperation(value = "Ativa turma")
	public ResponseEntity<Void> ativarTurma(
			@ApiParam(value = "ID da turma", required = true)
			Long turmaId);
	
	@ApiOperation(value = "Desativa turma")
	public ResponseEntity<Void> desativarTurma(
			@ApiParam(value = "ID da turma", required = true)
			Long turmaId);
	
	@ApiOperation(value = "Muda aluno de turma")
	public void mudarAlunoDeTurma(
			@ApiParam(value = "ID do aluno", required = true, type = "query")
			Long alunoId, 

			@ApiParam(value = "ID da turma", required = true, type = "query")
			Long turmaNovaId);
	
	@ApiOperation(value = "Busca aluno por nome")
	public Turma buscarPorNome(@ApiParam(value = "Nome do aluno", required = true, type = "query") String nome);
}