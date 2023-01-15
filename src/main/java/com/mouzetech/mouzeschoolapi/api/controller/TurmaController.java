package com.mouzetech.mouzeschoolapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mouzetech.mouzeschoolapi.api.model.input.AlocarProfessorEmTurmaInput;
import com.mouzetech.mouzeschoolapi.api.model.input.TurmaInput;
import com.mouzetech.mouzeschoolapi.api.model.output.GradeCurricularModel;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoAlunoModel;
import com.mouzetech.mouzeschoolapi.api.model.output.ResumoTurmaModel;
import com.mouzetech.mouzeschoolapi.api.model.output.TurmaModel;
import com.mouzetech.mouzeschoolapi.domain.model.Turma;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.StatusGeral;
import com.mouzetech.mouzeschoolapi.domain.repository.TurmaRepository;
import com.mouzetech.mouzeschoolapi.domain.service.CadastroAlunoTurmaService;
import com.mouzetech.mouzeschoolapi.domain.service.CadastroProfessorTurmaService;
import com.mouzetech.mouzeschoolapi.domain.service.CadastroTurmaMateriaProfessorService;
import com.mouzetech.mouzeschoolapi.domain.service.CadastroTurmaService;
import com.mouzetech.mouzeschoolapi.domain.service.MudarAlunoDeTurmaService;
import com.mouzetech.mouzeschoolapi.mapper.assembler.ResumoAlunoModelAssembler;
import com.mouzetech.mouzeschoolapi.mapper.assembler.ResumoTurmaModelAssembler;
import com.mouzetech.mouzeschoolapi.mapper.assembler.TurmaModelAssembler;
import com.mouzetech.mouzeschoolapi.openapi.controller.TurmaResourceOpenApi;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/turmas")
@AllArgsConstructor
public class TurmaController implements TurmaResourceOpenApi {

	private TurmaRepository turmaRepository;
	
	private CadastroTurmaService cadastroTurmaService;
	
	private CadastroProfessorTurmaService cadastroProfessorTurmaService;
	
	private CadastroAlunoTurmaService cadastroAlunoTurmaService;
	
	private MudarAlunoDeTurmaService mudarAlunoDeTurmaService;
	
	private CadastroTurmaMateriaProfessorService cadastroTurmaMateriaProfessorService;
	
	private TurmaModelAssembler turmaModelMapper;
		
	private ResumoAlunoModelAssembler resumoAlunoModelAssembler;
	
	private ResumoTurmaModelAssembler resumoTurmaModelAssembler;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public CollectionModel<ResumoTurmaModel> buscarTurmas(@RequestParam(required = false, defaultValue = "true") boolean ativo){
		List<Turma> listTurma = null;
		
		if(ativo == false) {
			listTurma = turmaRepository.findByStatusTurma(StatusGeral.DESATIVADA);
		} else {
			listTurma = turmaRepository.findByStatusTurma(StatusGeral.ATIVADA);
		}
		
		return resumoTurmaModelAssembler.toCollectionModel(listTurma);
	}
	
	@GetMapping(path = "/{turmaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public TurmaModel buscarPorId(@PathVariable Long turmaId) {
		return turmaModelMapper.toModel(cadastroTurmaService.buscarPorId(turmaId));
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/nome/{turmaNome}")
	public TurmaModel buscarPorNome(@PathVariable String turmaNome) {
		return turmaModelMapper.toModel(cadastroTurmaService.buscarPorNome(turmaNome));
	}
	
	@GetMapping(value = "/{turmaId}/grade-curricular", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public GradeCurricularModel buscarGradeCurricularTurma(@PathVariable Long turmaId){
		Turma turma = cadastroTurmaService.buscarPorId(turmaId);
		
		return cadastroTurmaMateriaProfessorService.buscarGradeCurricularPorTurma(turma);		
	}
	
	@GetMapping(value = "/{turmaId}/alunos", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<ResumoAlunoModel> buscarAlunosDaTurma(@PathVariable Long turmaId){
		Turma turma = cadastroTurmaService.buscarPorId(turmaId);
		
		return resumoAlunoModelAssembler.toCollectionModel(turma.getAlunos());
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<TurmaModel> cadastrar(@RequestBody @Valid TurmaInput dto){
		return ResponseEntity.ok(turmaModelMapper.toModel(cadastroTurmaService.cadastrar(dto)));
	}
	
	@PutMapping("/alocar-professor")
	@ResponseStatus(HttpStatus.OK)
	public void alocarProfessorEmTurma(@RequestBody AlocarProfessorEmTurmaInput dto){
		cadastroProfessorTurmaService.matricularProfessorEmTurma(dto.getProfessorId(), dto.getTurmaId(), dto.getMateriaId());
	}
	
	@PutMapping("/matricular-aluno")
	@ResponseStatus(HttpStatus.OK)
	public void matricularAlunoEmTurma(@RequestParam(required = true) Long alunoId, @RequestParam(required = true) Long turmaId){
		cadastroAlunoTurmaService.matricularAlunoNaTurma(alunoId, turmaId);
	}
	
	@PutMapping("/ativar/{turmaId}")
	public ResponseEntity<Void> ativarTurma(@PathVariable Long turmaId){
		cadastroTurmaService.ativar(turmaId);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/desativar/{turmaId}")
	public ResponseEntity<Void> desativarTurma(@PathVariable Long turmaId){
		cadastroTurmaService.desativar(turmaId);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/mudar-aluno-de-turma")
	@ResponseStatus(HttpStatus.OK)
	public void mudarAlunoDeTurma(@RequestParam(required =  true) Long alunoId, @RequestParam(required =  true) Long turmaNovaId){
		mudarAlunoDeTurmaService.mudarAlunoDeTurma(alunoId, turmaNovaId);
	}	
}