package com.mouzetech.mouzeschoolapi.domain.service;

import com.mouzetech.mouzeschoolapi.api.model.output.AlunosDaTurmaModel;

public interface TurmaQueryService {

	AlunosDaTurmaModel buscarAlunosDaTurma(Long turmaId);
	
}
