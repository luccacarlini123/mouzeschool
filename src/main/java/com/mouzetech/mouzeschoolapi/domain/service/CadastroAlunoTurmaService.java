package com.mouzetech.mouzeschoolapi.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mouzetech.mouzeschoolapi.domain.exception.NegocioException;
import com.mouzetech.mouzeschoolapi.domain.model.Aluno;
import com.mouzetech.mouzeschoolapi.domain.model.Turma;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.StatusGeral;
import com.mouzetech.mouzeschoolapi.domain.repository.TurmaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CadastroAlunoTurmaService {
	
	private TurmaRepository turmaRepository;
	
	private CadastroAlunoService cadastroAlunoService;
	
	private CadastroTurmaService cadastroTurmaService;
	
	@Transactional
	public void matricularAlunoNaTurma(Long alunoId, Long turmaId) {
		Aluno aluno = cadastroAlunoService.buscarPorId(alunoId);
		Turma turma = cadastroTurmaService.buscarPorId(turmaId);
		
		validarDadosAntesDeMatricularAlunoNaTurma(aluno, turma);
		
		turma.getAlunos().add(aluno);
		turmaRepository.save(turma);
	}

	private void validarDadosAntesDeMatricularAlunoNaTurma(Aluno aluno, Turma turma) {
		
		if(aluno.getMatricula().getStatus().equals(StatusGeral.DESATIVADA)) {
			throw new NegocioException("Não é possível matricular um aluno que esteja com a matrícula desativada.");
		}
		
		if(turma.desativada()) {
			throw new NegocioException("Não é possível matricular um aluno em uma turma desativada");
		}
		
		if(turma.alunoJaMatriculado(aluno)) {
			throw new NegocioException("O aluno já está matriculado na turma");
		}
		
		if(alunoMatriculadoEmOutraTurma(aluno.getId())) {
			throw new NegocioException("O aluno já está matriculado em outra turma");
		}
	}

	private boolean alunoMatriculadoEmOutraTurma(Long alunoId) {
		return turmaRepository.quantidadeMatriculaPorAlunoEmTurmaAtivada(alunoId) > 0;
	}	
}