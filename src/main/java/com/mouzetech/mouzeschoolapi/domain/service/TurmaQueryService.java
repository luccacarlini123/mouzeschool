package com.mouzetech.mouzeschoolapi.domain.service;

import com.mouzetech.mouzeschoolapi.domain.model.dto.AlunosDaTurmaDTO;

public interface TurmaQueryService {

	AlunosDaTurmaDTO buscarAlunosDaTurma(Long turmaId);
	
}
