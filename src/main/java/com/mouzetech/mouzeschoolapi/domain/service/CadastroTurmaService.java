package com.mouzetech.mouzeschoolapi.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mouzetech.mouzeschoolapi.api.model.input.CadastrarTurmaInput;
import com.mouzetech.mouzeschoolapi.domain.exception.TurmaNaoEncontradaException;
import com.mouzetech.mouzeschoolapi.domain.model.Turma;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.GrauEnsino;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.SerieEnsino;
import com.mouzetech.mouzeschoolapi.domain.model.enumeration.StatusGeral;
import com.mouzetech.mouzeschoolapi.domain.repository.TurmaRepository;
import com.mouzetech.mouzeschoolapi.mapper.TurmaModelMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CadastroTurmaService {

	private TurmaRepository turmaRepository;
	private TurmaModelMapper turmaModelMapper;
	
	public Turma buscarPorId(Long turmaId) {
		return turmaRepository.findById(turmaId)
					.orElseThrow(() -> new TurmaNaoEncontradaException(String.format("Não existe uma turma com o id %d", turmaId)));
	}
	
	@Transactional
	public Turma cadastrar(CadastrarTurmaInput dto) {
		Turma turma = turmaModelMapper.toEntity(dto);
		turma.setStatusTurma(StatusGeral.ATIVADA);
		turma.setGrauEnsino(GrauEnsino.toEnum(dto.getGrauEnsino()));
		turma.setSerieEnsino(SerieEnsino.toEnum(dto.getSerieEnsino()));
		return turmaRepository.save(turma);
	}
	
	@Transactional
	public void ativar(Long turmaId) {
		Turma turma = buscarPorId(turmaId);
		turma.ativar();
	}
	
	@Transactional
	public void desativar(Long turmaId) {
		Turma turma = buscarPorId(turmaId);
		turma.desativar();
	}
}	
