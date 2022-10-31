package com.mouzetech.mouzeschoolapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
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
import com.mouzetech.mouzeschoolapi.api.model.input.CadastrarTurmaInput;
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
import com.mouzetech.mouzeschoolapi.mapper.AlunoModelMapper;
import com.mouzetech.mouzeschoolapi.mapper.TurmaModelMapper;
import com.mouzetech.mouzeschoolapi.openapi.controller.TurmaResourceOpenApi;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/turmas")
@AllArgsConstructor
public class TurmaResource implements TurmaResourceOpenApi {

	private TurmaRepository turmaRepository;
	
	private CadastroTurmaService cadastroTurmaService;
	
	private CadastroProfessorTurmaService cadastroProfessorTurmaService;
	
	private CadastroAlunoTurmaService cadastroAlunoTurmaService;
	
	private MudarAlunoDeTurmaService mudarAlunoDeTurmaService;
	
	private CadastroTurmaMateriaProfessorService cadastroTurmaMateriaProfessorService;
	
	private TurmaModelMapper turmaModelMapper;
	
	private AlunoModelMapper alunoModelMapper;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ResumoTurmaModel> buscarTurmas(@RequestParam(required = false, defaultValue = "true") boolean ativo){
		List<Turma> listTurma = null;
		
		if(ativo == false) {
			listTurma = turmaRepository.findByStatusTurma(StatusGeral.DESATIVADA);
		} else {
			listTurma = turmaRepository.findByStatusTurma(StatusGeral.ATIVADA);
		}
		
		return turmaModelMapper.toCollectionResumoModel(listTurma);
	}
	
	@GetMapping("/{turmaId}")
	@ResponseStatus(HttpStatus.OK)
	public TurmaModel buscarPorId(@PathVariable Long turmaId) {
		return turmaModelMapper.toModel(cadastroTurmaService.buscarPorId(turmaId));
	}
	
	@GetMapping("/{turmaId}/grade-curricular")
	@ResponseStatus(HttpStatus.OK)
	public GradeCurricularModel buscarMateriasProfessoresPorTurma(@PathVariable Long turmaId){
		Turma turma = cadastroTurmaService.buscarPorId(turmaId);
		
		return cadastroTurmaMateriaProfessorService.buscarGradeCurricularPorTurma(turma);		
	}
	
	@GetMapping("/{turmaId}/alunos")
	@ResponseStatus(HttpStatus.OK)
	public List<ResumoAlunoModel> buscarAlunosDaTurma(@PathVariable Long turmaId){
		Turma turma = cadastroTurmaService.buscarPorId(turmaId);
		
		return alunoModelMapper.toCollectionResumoAlunoModel(turma.getAlunos());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastrarTurmaInput dto){
		return ResponseEntity.ok(cadastroTurmaService.cadastrar(dto));
	}
	
	@PostMapping("/alocar-professor")
	@ResponseStatus(HttpStatus.OK)
	public void alocarProfessorEmTurma(@RequestBody AlocarProfessorEmTurmaInput dto){
		cadastroProfessorTurmaService.matricularProfessorEmTurma(dto.getProfessorId(), dto.getTurmaId(), dto.getMateriaId());
	}
	
	@PostMapping("/matricular-aluno")
	@ResponseStatus(HttpStatus.OK)
	public void matricularAlunoEmTurma(@RequestParam(required = true) Long alunoId, @RequestParam(required = true) Long turmaId){
		cadastroAlunoTurmaService.matricularAlunoNaTurma(alunoId, turmaId);
	}
	
	@PutMapping("/ativar/{turmaId}")
	@ResponseStatus(HttpStatus.OK)
	public void ativarTurma(@PathVariable Long turmaId){
		cadastroTurmaService.ativar(turmaId);
	}
	
	@PutMapping("/desativar/{turmaId}")
	@ResponseStatus(HttpStatus.OK)
	public void desativarTurma(@PathVariable Long turmaId){
		cadastroTurmaService.desativar(turmaId);
	}
	
	@PutMapping("/mudar-aluno-de-turma")
	@ResponseStatus(HttpStatus.OK)
	public void mudarAlunoDeTurma(@RequestParam(required =  true) Long alunoId, @RequestParam(required =  true) Long turmaNovaId){
		mudarAlunoDeTurmaService.mudarAlunoDeTurma(alunoId, turmaNovaId);
	}	
}