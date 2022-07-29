package com.mouzetech.mouzeschoolapi.domain.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mouzetech.mouzeschoolapi.api.model.filter.NotaFilter;
import com.mouzetech.mouzeschoolapi.api.model.input.CadastrarNotaInput;
import com.mouzetech.mouzeschoolapi.api.model.output.NotasDaTurmaModel;
import com.mouzetech.mouzeschoolapi.api.model.output.RelacaoNotasDaTurmaModel;
import com.mouzetech.mouzeschoolapi.domain.exception.NegocioException;
import com.mouzetech.mouzeschoolapi.domain.model.Aluno;
import com.mouzetech.mouzeschoolapi.domain.model.Materia;
import com.mouzetech.mouzeschoolapi.domain.model.Nota;
import com.mouzetech.mouzeschoolapi.domain.model.Turma;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.StatusGeral;
import com.mouzetech.mouzeschoolapi.domain.repository.NotaRepository;
import com.mouzetech.mouzeschoolapi.mapper.NotaModelMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CadastroNotaService {

	@PersistenceContext
	private EntityManager entityManager;
	
	private NotaRepository notaRepostiory;
	
	private CadastroAlunoService cadastroAlunoService;
	
	private CadastroTurmaService cadastroTurmaService;
	
	private CadastroMateriaService cadastroMateriaService;
	
	private CadastroTurmaMateriaProfessorService cadastroTurmaMateriaProfessorService;
	
	private NotaModelMapper notaModelMapper;
	
	@Transactional
	public void cadastrarNota(CadastrarNotaInput dto) {
		Aluno aluno = cadastroAlunoService.buscarPorId(dto.getAlunoId());
		Turma turma = cadastroTurmaService.buscarPorId(dto.getTurmaId());
		Materia materia = cadastroMateriaService.buscarPorId(dto.getMateriaId());
		
		validarDadosAntesDeCadastrarNota(aluno, turma, materia, dto);
		
		Nota nota = new Nota(null, aluno, materia, turma, dto.getValor(), dto.getBimestre(), null);
		
		notaRepostiory.save(nota);
	}
	
//	public List<NotasDaTurmaModel> buscarNotasAlunoPorTurma(Long alunoId, Long turmaId) {
//		Aluno aluno = cadastroAlunoService.buscarPorId(alunoId);
//		Turma turma = cadastroTurmaService.buscarPorId(turmaId);
//		
//		return notaRepostiory.findByAlunoAndTurma(aluno, turma);
//	}
	
	public NotasDaTurmaModel buscarNotasDaTurma(Long turmaId, NotaFilter notaFilter) {
		Turma turma = cadastroTurmaService.buscarPorId(turmaId);
		
		var builder = entityManager.getCriteriaBuilder();
		var query = builder.createQuery(Nota.class);
		var root = query.from(Nota.class);
		
		root.fetch("aluno").fetch("matricula");
		root.fetch("materia");
		
		var joinAluno = root.join("aluno");
		
		var joinMateria = root.join("materia");
		
		var predicates = new ArrayList<Predicate>();
		
		predicates.add(builder.equal(root.get("turma"), turma));
		
		if(StringUtils.isNotBlank(notaFilter.getAluno())) {
			predicates.add(builder.like(joinAluno.get("nome"), "%" + notaFilter.getAluno() + "%"));
		}
		
		if(notaFilter.getBimestre() != null ) {
			predicates.add(builder.equal(root.get("bimestre"), notaFilter.getBimestre()));
		}
		
		if(StringUtils.isNotBlank(notaFilter.getMateria())) {
			predicates.add(builder.like(joinMateria.get("nome"), "%" + notaFilter.getMateria() + "%"));
		}
		
		query.where(predicates.toArray(new Predicate[0]));
		
		
		List<Nota> notas = entityManager.createQuery(query).getResultList();
		
		NotasDaTurmaModel notasModel = new NotasDaTurmaModel();
		
		if(!notas.isEmpty()) {
			notasModel.setTurma(turma.getNome());
			
			List<RelacaoNotasDaTurmaModel> relacaoNotasDaTurmaModelCollection = 
						notaModelMapper.toRelacaoNotasDaTurmaModelCollection(notas);
			
			notasModel.getNotas().addAll(relacaoNotasDaTurmaModelCollection);
		}
		return notasModel;
	}

	private void validarDadosAntesDeCadastrarNota(Aluno aluno, Turma turma, Materia materia, CadastrarNotaInput dto) {
		
		if(turma.desativada()) {
			throw new NegocioException("Não é possível cadastrar nota em uma turma desativada");
		}
		
		if(materia.desativada()) {
			throw new NegocioException("Não é possível cadastrar nota em uma matéria desativada");
		}
		
		if(!turma.alunoJaMatriculado(aluno)) {
			throw new NegocioException("Não foi possível lançar a nota, o aluno não está matriculado nessa turma");
		}
		
		if(aluno.getMatricula().getStatus().equals(StatusGeral.DESATIVADA)) {
			throw new NegocioException("Não é possível lançar nota para um aluno com a matrícula desativada.");
		}
		
		if(!cadastroTurmaMateriaProfessorService.isMateriaAssociadaNaTurma(materia, turma)) {
			throw new NegocioException("Não foi possível lançar a nota, a matéria não está associada a turma");
		}
		
		if(dto.getValor() == null || dto.getValor() < 0.0 || dto.getValor() > 10.0) {
			throw new NegocioException("Não foi possível lançar a nota, o valor não pode ser nulo e deve estar entre 0 e 10");
		}
		
		if(dto.getBimestre() == null || dto.getBimestre() < 1 || dto.getBimestre() > 4) {
			throw new NegocioException("Não foi possível lançar a nota, só são permitidos bimestres entre 1 e 4");
		}
		
		if(existeNotaCadastradaNoBimestre(aluno.getId(), turma.getId(), materia.getId(), dto.getBimestre())) {
			throw new NegocioException("Já existe nota cadastrada nesse bimestre");
		}
	}
	
	private boolean existeNotaCadastradaNoBimestre(Long alunoId, Long turmaId, Long materiaId, Short bimestre) {
		return notaRepostiory.existeNotaNoBimestre(alunoId, turmaId, materiaId, bimestre) > 0;
	}
}
