package com.mouzetech.mouzeschoolapi.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mouzetech.mouzeschoolapi.api.model.filter.NotaFilter;
import com.mouzetech.mouzeschoolapi.api.model.input.CadastrarNotaInput;
import com.mouzetech.mouzeschoolapi.api.model.output.NotaModel;
import com.mouzetech.mouzeschoolapi.api.model.output.NotasDaTurmaModel;
import com.mouzetech.mouzeschoolapi.domain.repository.NotaRepository;
import com.mouzetech.mouzeschoolapi.domain.service.CadastroNotaService;
import com.mouzetech.mouzeschoolapi.mapper.NotaModelMapper;
import com.mouzetech.mouzeschoolapi.openapi.controller.NotaResourceOpenApi;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/notas")
@AllArgsConstructor
public class NotaResource implements NotaResourceOpenApi {

	private NotaRepository notaRepository;
	private CadastroNotaService cadastroNotaService;
	private NotaModelMapper notaModelMapper;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<NotaModel>> buscarNotas(){
		return ResponseEntity.ok(notaModelMapper.toCollectionNotaModel(notaRepository.findAll()));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void cadastrarNota(@RequestBody CadastrarNotaInput dto){
		cadastroNotaService.cadastrarNota(dto);
	}
	
	@GetMapping(path = "/turma/{turmaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public NotasDaTurmaModel buscarNotasPorTurma(@PathVariable Long turmaId, NotaFilter notaFilter){
		return cadastroNotaService.buscarNotasDaTurma(turmaId, notaFilter);
	}
}