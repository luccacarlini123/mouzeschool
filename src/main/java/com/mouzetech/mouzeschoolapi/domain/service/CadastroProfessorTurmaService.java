package com.mouzetech.mouzeschoolapi.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mouzetech.mouzeschoolapi.domain.exception.NegocioException;
import com.mouzetech.mouzeschoolapi.domain.model.Materia;
import com.mouzetech.mouzeschoolapi.domain.model.Professor;
import com.mouzetech.mouzeschoolapi.domain.model.Turma;
import com.mouzetech.mouzeschoolapi.domain.model.TurmaMateriaProfessor;
import com.mouzetech.mouzeschoolapi.domain.model.TurmaMateriaProfessorId;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.StatusGeral;
import com.mouzetech.mouzeschoolapi.domain.repository.ProfessorRepository;
import com.mouzetech.mouzeschoolapi.domain.repository.TurmaMateriaProfessorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CadastroProfessorTurmaService {

	private ProfessorRepository professorRepository;
	
	private CadastroTurmaService cadastroTurmaService;
	
	private CadastroMateriaService cadastroMateriaService;
	
	private CadastroTurmaMateriaProfessorService cadastroTurmaMateriaProfessorService;
	
	private TurmaMateriaProfessorRepository turmaMateriaProfessorRepository;
	
	@Transactional
	public void matricularProfessorEmTurma(Long professorId, Long turmaId, Long materiaId) {
		
		Turma turma = cadastroTurmaService.buscarPorId(turmaId);
		Materia materia = cadastroMateriaService.buscarPorId(materiaId);
		
		TurmaMateriaProfessorId turmaMateriaProfessorId = new TurmaMateriaProfessorId();
		turmaMateriaProfessorId.setMateria(materia);
		turmaMateriaProfessorId.setTurma(turma);
		
		TurmaMateriaProfessor turmaMateriaProfessor = new TurmaMateriaProfessor();
		turmaMateriaProfessor.setTurmaMateriaProfessorId(turmaMateriaProfessorId);
		
		Optional<Professor> optionalProfessor = Optional.empty();
		
		if(professorId != null) {
			optionalProfessor = professorRepository.findById(professorId);
			if(optionalProfessor.isPresent()) {
				turmaMateriaProfessor.setProfessor(optionalProfessor.get());		
			}
		}
		
		validarDadosAntesDeMatricularProfessorNaTurma(turma, materia, optionalProfessor);
		
		turmaMateriaProfessorRepository.save(turmaMateriaProfessor);
	}

	private void validarDadosAntesDeMatricularProfessorNaTurma(Turma turma, Materia materia, Optional<Professor> optionalProfessor) {
		
		if(turma.getStatusTurma().equals(StatusGeral.DESATIVADA)) {
			throw new NegocioException("Não é possível matricular um professor em uma turma desativada");
		}
		
		if(materia.getStatusMateria().equals(StatusGeral.DESATIVADA)) {
			throw new NegocioException("Não é possível matricular um professor com uma matéria desativada");
		}
		
		if(optionalProfessor.isPresent() && optionalProfessor.get().getMatricula().getStatus().equals(StatusGeral.DESATIVADA)) {
			throw new NegocioException("Não é possível associar um professor com a matrícula desativada a uma matéria");
		}
		
		/*O if comentado abaixo serve para não permitir a mudança de professor em uma matéria de uma turma*/
		
//		if(cadastroTurmaMateriaProfessorService.existeProfessorAlocadoEmMateriaDaTurma(materia, turma)) {
//			throw new NegocioException("Já existe professor alocado na matéria");
//		}
		
		if(optionalProfessor.isEmpty()) {
			if(cadastroTurmaMateriaProfessorService.isMateriaAssociadaNaTurma(materia, turma)) {
				throw new NegocioException("A matéria já está associada a turma.");
			}			
		}
		
		if(!turma.getGrauEnsino().equals(materia.getGrauEnsino())
				|| !turma.getSerieEnsino().equals(materia.getSerieEnsino())) {
			throw new NegocioException("A matéria precisa ser da mesma série e grau de ensino da turma");
		}
		
	}
}