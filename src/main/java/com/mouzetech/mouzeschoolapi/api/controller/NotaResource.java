package com.mouzetech.mouzeschoolapi.api.controller;

import org.springframework.http.HttpStatus;
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
import com.mouzetech.mouzeschoolapi.api.model.output.NotasDaTurmaModel;
import com.mouzetech.mouzeschoolapi.domain.repository.NotaRepository;
import com.mouzetech.mouzeschoolapi.domain.service.CadastroNotaService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/notas")
@AllArgsConstructor
public class NotaResource {

	private NotaRepository notaRepository;
	private CadastroNotaService cadastroNotaService;
	
	@GetMapping
	public ResponseEntity<?> buscarNotas(){
		return ResponseEntity.ok(notaRepository.findAll());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void cadastrarNota(@RequestBody CadastrarNotaInput dto){
		cadastroNotaService.cadastrarNota(dto);
	}
	
//	@GetMapping("/buscar-por-aluno-e-turma")
//	public ResponseEntity<List<NotasDaTurmaModel>> buscarNotasAlunoPorTurma(@RequestBody BuscarNotasPorAlunoTurmaInput dto){
//		return ResponseEntity.ok(notaModelMapper.toCollectionDTO(
//				cadastroNotaService.buscarNotasAlunoPorTurma(dto.getAlunoId(), dto.getTurmaId())));
//	}
	
	@GetMapping("/turma/{turmaId}")
	@ResponseStatus(HttpStatus.OK)
	public NotasDaTurmaModel buscarNotasPorTurma(@PathVariable Long turmaId, NotaFilter notaFilter){
		return cadastroNotaService.buscarNotasDaTurma(turmaId, notaFilter);
	}
}