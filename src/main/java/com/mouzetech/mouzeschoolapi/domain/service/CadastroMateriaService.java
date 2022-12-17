package com.mouzetech.mouzeschoolapi.domain.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mouzetech.mouzeschoolapi.api.model.input.CadastrarMateriaInput;
import com.mouzetech.mouzeschoolapi.domain.exception.MateriaNaoEncontradaException;
import com.mouzetech.mouzeschoolapi.domain.exception.NegocioException;
import com.mouzetech.mouzeschoolapi.domain.model.Materia;
import com.mouzetech.mouzeschoolapi.domain.model.TurmaMateriaProfessor;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.GrauEnsino;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.SerieEnsino;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.StatusGeral;
import com.mouzetech.mouzeschoolapi.domain.repository.MateriaRepository;
import com.mouzetech.mouzeschoolapi.domain.repository.TurmaMateriaProfessorRepository;
import com.mouzetech.mouzeschoolapi.mapper.assembler.MateriaModelAssembler;
import com.mouzetech.mouzeschoolapi.mapper.disassembler.MateriaModelDisassembler;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CadastroMateriaService {

	private MateriaRepository materiaRepository;
	private MateriaModelAssembler materiaModelMapper;
	private TurmaMateriaProfessorRepository turmaMateriaProfessorRepository;
	private MateriaModelDisassembler materiaModelDisassembler;
	
	public Materia buscarPorId(Long materiaId) {
		return materiaRepository.findById(materiaId)
					.orElseThrow(() -> new MateriaNaoEncontradaException(String.format("Não existe uma matéria com o id %d", materiaId)));
	}
	
	public void excluir(Long materiaId) {
		try {
			Materia materia = buscarPorId(materiaId);
			materiaRepository.delete(materia);
		} catch(DataIntegrityViolationException e) {
			throw new NegocioException("Não é possível excluir uma matéria que está associada a uma turma.");
		}
	}
	
	public List<Materia> buscarPorNomeContaining(String nome) {
		List<Materia> materias = materiaRepository.findByNomeContaining(nome);
		if(materias.isEmpty()) {
			throw new MateriaNaoEncontradaException(String.format("Não foi possível encontrar nenhuma matéria com o nome: %s", nome));
		}
		return materias;
	}
	
	@Transactional
	public Materia cadastrar(CadastrarMateriaInput dto) {
		Materia materia = materiaModelDisassembler.toEntity(dto);		
		materia.setGrauEnsino(GrauEnsino.toEnum(dto.getGrauEnsino()));
		materia.setSerieEnsino(SerieEnsino.toEnum(dto.getSerieEnsino()));
		materia.setStatusMateria(StatusGeral.DESATIVADA);
		return materiaRepository.save(materia);
	}
	
	@Transactional
	public void ativar(Long materiaId) {
		Materia materia = buscarPorId(materiaId);
		materia.ativar();
	}
	
	@Transactional
	public void desativar(Long materiaId) {
		Materia materia = buscarPorId(materiaId);
		List<TurmaMateriaProfessor> turmasMateriaProfessor = 
				turmaMateriaProfessorRepository.buscarTurmasMateriaAssociada(materia);
		
		if(turmasMateriaProfessor.stream()
			.anyMatch(tmp -> tmp.getTurmaMateriaProfessorId().getTurma().getStatusTurma()
					.equals(StatusGeral.ATIVADA))) {
			throw new NegocioException("Existem turmas associadas a matéria, não é possível desativa-la");
		}
		
		materia.desativar();
	}
}