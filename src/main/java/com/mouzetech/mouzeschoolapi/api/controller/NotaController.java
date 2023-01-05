package com.mouzetech.mouzeschoolapi.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mouzetech.mouzeschoolapi.api.ApiLinkBuilder;
import com.mouzetech.mouzeschoolapi.api.model.filter.NotaFilter;
import com.mouzetech.mouzeschoolapi.api.model.input.NotaInput;
import com.mouzetech.mouzeschoolapi.api.model.output.NotasDaTurmaModel;
import com.mouzetech.mouzeschoolapi.domain.service.CadastroNotaService;
import com.mouzetech.mouzeschoolapi.openapi.controller.NotaResourceOpenApi;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/notas")
@AllArgsConstructor
public class NotaController implements NotaResourceOpenApi {
	private CadastroNotaService cadastroNotaService;
	private ApiLinkBuilder apiLinkBuilder;
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void cadastrarNota(@RequestBody NotaInput dto){
		cadastroNotaService.cadastrarNota(dto);
	}
	
	@GetMapping(path = "/turma/{turmaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public NotasDaTurmaModel buscarNotasPorTurma(@PathVariable Long turmaId, NotaFilter notaFilter){
		NotasDaTurmaModel notasDaTurmaModel = cadastroNotaService.buscarNotasDaTurma(turmaId, notaFilter);
		
		notasDaTurmaModel.add(apiLinkBuilder.linkToTurma("turma", notasDaTurmaModel.getTurma()));
		
		notasDaTurmaModel.getNotas().forEach(nota -> {
			nota.add(apiLinkBuilder.linkToAlunos("aluno", nota.getNomeAluno()));
			nota.add(apiLinkBuilder.linkToMateria("materia", nota.getNomeMateria()));
		});
		
		return notasDaTurmaModel;
	}
}