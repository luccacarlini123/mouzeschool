package com.mouzetech.mouzeschoolapi.domain.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mouzetech.mouzeschoolapi.domain.exception.NegocioException;
import com.mouzetech.mouzeschoolapi.domain.model.Aluno;
import com.mouzetech.mouzeschoolapi.domain.model.Materia;
import com.mouzetech.mouzeschoolapi.domain.model.Nota;
import com.mouzetech.mouzeschoolapi.domain.model.Turma;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.StatusGeral;
import com.mouzetech.mouzeschoolapi.domain.repository.NotaRepository;
import com.mouzetech.mouzeschoolapi.domain.repository.TurmaMateriaProfessorRepository;
import com.mouzetech.mouzeschoolapi.domain.repository.TurmaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MudarAlunoDeTurmaService {

	@PersistenceContext
	private EntityManager entityManager;
	
	private TurmaRepository turmaRepository;
	private CadastroAlunoService cadastroAlunoService;
	private CadastroTurmaService cadastroTurmaService;
	private NotaRepository notaRepository;
	private TurmaMateriaProfessorRepository turmaMateriaProfessorRepository;
	
	@Transactional
	public void mudarAlunoDeTurma(Long alunoId, Long idTurmaFutura) {
		//preciso verificar se a matricula do aluno está ativa
		Aluno aluno = cadastroAlunoService.buscarPorId(alunoId);

		Turma turmaAtual = encontrarTurmaComMatriculaAtivaDoAluno(aluno);

		Turma turmaNova = buscarTurmaFuturaDoAluno(idTurmaFutura, turmaAtual);
		
		verificarSeTurmasCompativeis(turmaAtual, turmaNova);
		
		List<Nota> notasTurmaAtual = notaRepository.findByAlunoAndTurma(aluno, turmaAtual);
		notaRepository.deleteAll(notasTurmaAtual);
		
		turmaAtual.getAlunos().remove(aluno);
		
		turmaNova.getAlunos().add(aluno);
		
		List<Nota> notasTurmaNova = prepararNotasParaTurmaNova(notasTurmaAtual, turmaNova);
		notaRepository.saveAll(notasTurmaNova);
	}

	private Turma encontrarTurmaComMatriculaAtivaDoAluno(Aluno aluno) {
		Turma turmaAtualDoAluno = null;
		
		List<Turma> turmasAtivas = turmaRepository.findByStatusTurma(StatusGeral.ATIVADA);
		
		for(Turma turma : turmasAtivas) {
			for(Aluno alunoEncontrado : turma.getAlunos()) {
				if(alunoEncontrado.equals(aluno)) {
					turmaAtualDoAluno = turma;
					break;
				}
			}
		}
		
		if(turmaAtualDoAluno == null) {
			throw new NegocioException("O aluno \""+aluno.getNome()+"\" não está matriculado em nenhuma turma, por isso não é possível muda-lo de turma.");
		}
		
		return turmaAtualDoAluno;
	}
	
	private Turma buscarTurmaFuturaDoAluno(Long idTurmaFutura, Turma turmaAtual) {
		Turma turmaFutura = cadastroTurmaService.buscarPorId(idTurmaFutura);
		
		if(turmaFutura.desativada()) {
			throw new NegocioException("A "+turmaFutura.getNome()+" está desativada.");
		}
		
		if(turmaFutura.equals(turmaAtual)) {
			throw new NegocioException("Não é possível mudar um aluno para uma turma que ele já está matriculado.");
		}
		return turmaFutura;
	}
	
	private void verificarSeTurmasCompativeis(Turma turmaAtual, Turma turmaNova) {
		if(!(turmaAtual.getSerieEnsino().equals(turmaNova.getSerieEnsino()) 
				&& turmaAtual.getGrauEnsino().equals(turmaNova.getGrauEnsino()))) {
			throw new NegocioException("Para mudar um aluno de turma, as turmas precisam ser do mesmo grau e série de ensino.");
		}
		
		if(!materiasCompativeisParaMudarNotasDeTurma(turmaAtual, turmaNova)) {
			throw new NegocioException("As turmas não possuem matérias correspondentes para mudar o aluno, por isso a operação deverá ser feita manualmente.");
		}
	}
	
	private boolean materiasCompativeisParaMudarNotasDeTurma(Turma turmaAtual, Turma turmaNova) {
		List<Materia> materiasTurmaAtual = turmaMateriaProfessorRepository.buscarMateriasDaTurma(turmaAtual.getId());
		
		List<Materia> materiasTurmaNova = turmaMateriaProfessorRepository.buscarMateriasDaTurma(turmaNova.getId());
		
		return materiasTurmaAtual.equals(materiasTurmaNova);
	}
	
	private List<Nota> prepararNotasParaTurmaNova(List<Nota> notasTurmaAtual, Turma turmaNova) {
		List<Nota> notasTurmaNova = new ArrayList<>();
		if(!notasTurmaAtual.isEmpty()) {
			for(Nota nota : notasTurmaAtual) {
				entityManager.detach(nota);
				nota.setId(null);
				nota.setTurma(turmaNova);
				notasTurmaNova.add(nota);
			}
			return notasTurmaNova;
		}
		return notasTurmaNova;
	}
	
}
