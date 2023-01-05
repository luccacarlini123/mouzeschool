package com.mouzetech.mouzeschoolapi.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import com.mouzetech.mouzeschoolapi.api.controller.AlunoController;
import com.mouzetech.mouzeschoolapi.api.controller.CidadeController;
import com.mouzetech.mouzeschoolapi.api.controller.EstadoController;
import com.mouzetech.mouzeschoolapi.api.controller.MateriaController;
import com.mouzetech.mouzeschoolapi.api.controller.NotaController;
import com.mouzetech.mouzeschoolapi.api.controller.ProfessorController;
import com.mouzetech.mouzeschoolapi.api.controller.TurmaController;

@Component
public class ApiLinkBuilder {

	public Link linkToAlunos(String rel) {
		return linkTo(AlunoController.class)
				.withRel(rel);
	}
	
	public Link linkToAlunos(String rel, String nome) {
		return linkTo(methodOn(AlunoController.class).buscarPorNome(nome))
				.withRel(rel);
	}
	
	public Link linkToEnderecoAluno(Long alunoId, String rel) {
		return linkTo(methodOn(AlunoController.class).buscarEndereco(alunoId))
				.withRel(rel);
	}
	
	public Link linkToAtivarMatriculaAluno(Long alunoId, String rel) {
		return linkTo(methodOn(AlunoController.class).ativarMatricula(alunoId))
				.withRel(rel);
	}
	
	public Link linkToDesativarMatriculaAluno(Long alunoId, String rel) {
		return linkTo(methodOn(AlunoController.class).desativarMatricula(alunoId))
				.withRel(rel);
	}
	
	public Link linkToEstado(Long estadoId, String rel) {
		return linkTo(methodOn(EstadoController.class).buscarPorId(estadoId))
				.withRel(rel);
	}
	
	public Link linkToEstados(String rel) {
		return linkTo(EstadoController.class)
				.withRel(rel);
	}
	
	public Link linkToCidade(Long cidadeId, String rel) {
		return linkTo(methodOn(CidadeController.class).buscarPorId(cidadeId))
				.withRel(rel);
	}
	
	public Link linkToCidades(String rel) {
		return linkTo(CidadeController.class)
				.withRel(rel);
	}
	
	public Link linkToMateria(String rel, Long id) {
		return linkTo(methodOn(MateriaController.class).buscarPorId(id))
				.withRel(rel);
	}
	
	public Link linkToMateria(String rel, String nome) {
		return linkTo(methodOn(MateriaController.class).buscarPorNomeContaining(nome))
				.withRel(rel);
	}
	
	public Link linkToMaterias(String rel) {
		return linkTo(MateriaController.class)
				.withRel(rel);
	}
	
	public Link linkToAtivarMateria(String rel, Long id) {
		return linkTo(methodOn(MateriaController.class).ativarMateria(id))
				.withRel(rel);
	}
	
	public Link linkToDesativarMateria(String rel, Long id) {
		return linkTo(methodOn(MateriaController.class).desativarMateria(id))
				.withRel(rel);
	}
	
	public Link linkToTurma(String rel, String nome) {
		return linkTo(methodOn(TurmaController.class).buscarPorNome(nome))
				.withRel(rel);
	}
	
	public Link linkToTurmas(String rel) {
		return linkTo(TurmaController.class)
				.withRel(rel);
	}
	
	public Link linkToDesativarTurma(String rel, Long id) {
		return linkTo(methodOn(TurmaController.class).desativarTurma(id))
				.withRel(rel);
	}
	
	public Link linkToAtivarTurma(String rel, Long id) {
		return linkTo(methodOn(TurmaController.class).ativarTurma(id))
				.withRel(rel);
	}
	
	public Link linkToGradeCurricularTurma(String rel, Long id) {
		return linkTo(methodOn(TurmaController.class).buscarGradeCurricularTurma(id))
				.withRel(rel);
	}
	
	public Link linkToProfessores(String rel) {
		return linkTo(ProfessorController.class)
				.withRel(rel);
	}
	
	public Link linkToProfessor(String rel, Long id) {
		return linkTo(methodOn(ProfessorController.class).buscarPorId(id))
				.withRel(rel);
	}
	
	public Link linkToAtivarMatriculaProfessor(String rel, Long id) {
		return linkTo(methodOn(ProfessorController.class).ativarMatricula(id))
				.withRel(rel);
	}
	
	public Link linkToDesativarMatriculaProfessor(String rel, Long id) {
		return linkTo(methodOn(ProfessorController.class).desativarMatricula(id))
				.withRel(rel);
	}
	
	public Link linkToNotasDaTurma(String rel) {
		TemplateVariables templateVariables = new TemplateVariables(
				new TemplateVariable("aluno", VariableType.REQUEST_PARAM),
				new TemplateVariable("materia", VariableType.REQUEST_PARAM),
				new TemplateVariable("bimestre", VariableType.REQUEST_PARAM));
		
		String notasUrl = linkTo(methodOn(NotaController.class).buscarNotasPorTurma(null, null)).toUri().toString();
		
		return new Link(UriTemplate.of(notasUrl, templateVariables), rel);
	}
}